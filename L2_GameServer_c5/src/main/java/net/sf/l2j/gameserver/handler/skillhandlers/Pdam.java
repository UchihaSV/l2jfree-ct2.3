/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package net.sf.l2j.gameserver.handler.skillhandlers;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.handler.ISkillHandler;
import net.sf.l2j.gameserver.lib.Rnd;
import net.sf.l2j.gameserver.model.L2Character;
import net.sf.l2j.gameserver.model.L2Effect;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.L2Object;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.L2Skill.SkillType;
import net.sf.l2j.gameserver.model.actor.instance.L2NpcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2RaidBossInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2SummonInstance;
import net.sf.l2j.gameserver.serverpackets.SystemMessage;
import net.sf.l2j.gameserver.skills.Formulas;
import net.sf.l2j.gameserver.templates.L2WeaponType;

import org.apache.log4j.Logger;

/**
 * This class ...
 * 
 * @version $Revision: 1.1.2.7.2.16 $ $Date: 2005/04/06 16:13:49 $
 */

public class Pdam implements ISkillHandler
{
    // all the items ids that this handler knowns
    private static Logger _log = Logger.getLogger(Pdam.class);

    /* (non-Javadoc)
     * @see net.sf.l2j.gameserver.handler.IItemHandler#useItem(net.sf.l2j.gameserver.model.L2PcInstance, net.sf.l2j.gameserver.model.L2ItemInstance)
     */
    private static SkillType[] _skillIds = {SkillType.PDAM,
    /* SkillType.CHARGEDAM */
    };

    /* (non-Javadoc)
     * @see net.sf.l2j.gameserver.handler.IItemHandler#useItem(net.sf.l2j.gameserver.model.L2PcInstance, net.sf.l2j.gameserver.model.L2ItemInstance)
     */
    public void useSkill(L2Character activeChar, L2Skill skill, L2Object[] targets)
    {
        if (activeChar.isAlikeDead()) return;

        int damage = 0;

        if (_log.isDebugEnabled())
            if (_log.isDebugEnabled()) _log.debug("Begin Skill processing in Pdam.java " + skill.getSkillType());

        for (int index = 0; index < targets.length; index++)
        {
            L2Character target = (L2Character) targets[index];
            L2ItemInstance weapon = activeChar.getActiveWeaponInstance();
            if (activeChar instanceof L2PcInstance && target instanceof L2PcInstance
                && target.isAlikeDead() && target.isFakeDeath())
            {
                target.stopFakeDeath(null);
            }
            else if (target.isAlikeDead()) continue;

            boolean dual = activeChar.isUsingDualWeapon();
            boolean shld = Formulas.getInstance().calcShldUse(activeChar, target);
            //boolean crit = Formulas.getInstance().calcCrit(activeChar.getCriticalHit(target, skill));
            boolean soul = (weapon != null
                && weapon.getChargedSoulshot() == L2ItemInstance.CHARGED_SOULSHOT && weapon.getItemType() != L2WeaponType.DAGGER);
            if (skill.ignoreShld())
            {
                shld = false;
            }
            damage = (int) Formulas.getInstance().calcPhysDam(activeChar, target, skill, shld, false,
                                                              dual, soul);
            if (skill.isCritical())
            {
                if (Rnd.get(100) < 15)
                activeChar.sendPacket(new SystemMessage(SystemMessage.CRITICAL_HIT));
                damage = damage * 2;
            }
            if (damage > 5000 && activeChar instanceof L2PcInstance)
            {
                String name = "";
                if (target instanceof L2RaidBossInstance) name = "RaidBoss ";
                if (target instanceof L2NpcInstance)
                    name += target.getName() + "(" + ((L2NpcInstance) target).getTemplate().npcId + ")";
                if (target instanceof L2PcInstance)
                    name = target.getName() + "(" + target.getObjectId() + ") ";
                name += target.getLevel() + " lvl";
                _log.info(activeChar.getName() + "(" + activeChar.getObjectId() + ") "
                    + activeChar.getLevel() + " lvl did damage " + damage + " with skill "
                    + skill.getName() + "(" + skill.getId() + ") to " + name);
            }

            if (target instanceof L2NpcInstance)
            {
                if (target.isChampion())
                {
                    damage /= Config.CHAMPION_HP;
                }
            }

            //target.reduceCurrentHp(damage, activeChar);
            if (soul && weapon != null) weapon.setChargedSoulshot(L2ItemInstance.CHARGED_NONE);

            if (damage > 0)
            {
                if (activeChar instanceof L2PcInstance)
                {
                    SystemMessage sm = new SystemMessage(SystemMessage.YOU_DID_S1_DMG);
                    sm.addNumber(damage);
                    activeChar.sendPacket(sm);
                }
                if (activeChar instanceof L2SummonInstance)
                    ((L2SummonInstance) activeChar).getOwner().sendPacket(
                                                                          new SystemMessage(
                                                                                            SystemMessage.SUMMON_GAVE_DAMAGE_OF_S1).addNumber(damage));
               
                if (skill.hasEffects())
                {
                    // activate attacked effects, if any
                    target.stopEffect(skill.getId());
                    if (target.getEffect(skill.getId()) != null)
                        target.removeEffect(target.getEffect(skill.getId()));
                    if (Formulas.getInstance().calcSkillSuccess(activeChar, target, skill, false, false,
                                                                false))
                    {
                        skill.getEffects(activeChar, target);
                        SystemMessage sm = new SystemMessage(SystemMessage.YOU_FEEL_S1_EFFECT);
                        sm.addSkillName(skill.getId());
                        target.sendPacket(sm);
                    }
                    else
                    {
                        SystemMessage sm = new SystemMessage(139);
                        sm.addString(target.getName());
                        sm.addSkillName(skill.getId());
                        activeChar.sendPacket(sm);
                    }
                }
                if (target.isPetrified())
                {damage= 0;}
                target.reduceCurrentHp(damage, activeChar);
            }
            else activeChar.sendPacket(new SystemMessage(SystemMessage.ATTACK_FAILED));
        }
        //self Effect :]
        L2Effect effect = activeChar.getEffect(skill.getId());        
        if (effect != null && effect.isSelfEffect())        
        {            
           //Replace old effect with new one.            
           effect.exit();        
        }        
        skill.getEffectsSelf(activeChar);        
        if (skill.isSuicideAttack())
        {
           activeChar.doDie(null);
           activeChar.setCurrentHp(0);
        }        
    }

    public SkillType[] getSkillIds()
    {
        return _skillIds;
    }
}