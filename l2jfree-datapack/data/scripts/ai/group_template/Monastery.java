/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.group_template;

import java.util.Collection;

import javolution.util.FastList;

import com.l2jfree.gameserver.ai.CtrlIntention;
import com.l2jfree.gameserver.datatables.SkillTable;
import com.l2jfree.gameserver.model.L2Object;
import com.l2jfree.gameserver.model.L2Skill;
import com.l2jfree.gameserver.model.actor.L2Attackable;
import com.l2jfree.gameserver.model.actor.L2Character;
import com.l2jfree.gameserver.model.actor.L2Npc;
import com.l2jfree.gameserver.model.actor.L2Playable;
import com.l2jfree.gameserver.model.actor.L2Summon;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.actor.instance.L2PetInstance;
import com.l2jfree.gameserver.network.serverpackets.NpcSay;
import com.l2jfree.gameserver.templates.skills.L2SkillType;
import com.l2jfree.gameserver.util.Util;
import com.l2jfree.tools.random.Rnd;

public class Monastery extends L2AttackableAIScript
{
	static final int[] mobs1 = {22124, 22125, 22126, 22127, 22129};
	static final int[] mobs2 = {22134, 22135};
	static final String[] text = {
		"You cannot carry a weapon without authorization!",
		"name, why would you choose the path of darkness?!",
		"name! How dare you defy the will of Einhasad!"
	};
    public Monastery(int questId, String name, String descr)
    {
        super(questId, name, descr);
        registerMobs(mobs1);
        registerMobs(mobs2);
    }

    @Override
    public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isPet)
    {
    	if (equals(mobs1,npc.getNpcId()) && !npc.isInCombat())
    	{
    		if (player.getActiveWeaponInstance() != null)
    		{
    			npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[0]));
    			switch (npc.getNpcId())
    			{
    				case 22124:
    				case 22126:
    				{
    					L2Skill skill = SkillTable.getInstance().getInfo(4589,8);
    	    			npc.setTarget(player);
    	    			npc.doCast(skill);
    	    			break;
    				}
    				default:
    				{
    					npc.setIsRunning(true);
    	    			((L2Attackable) npc).addDamageHate(player, 0, 999);
    	    			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
    	    			break;
    				}
    			}
    		}
    		else if (!player.isInCombat())
    			return null;
    	}
        return super.onAggroRangeEnter(npc, player, isPet);
    }

    @Override
    public String onSkillSee(L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
	{
    	if (equals(mobs2,npc.getNpcId()))
    	{
    		if (skill.getSkillType() == L2SkillType.AGGDAMAGE && targets.length != 0)
    		{
    			for (L2Object obj : targets)
    			{
    				if (obj.equals(npc))
    				{
    					npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[Rnd.get(2)+1].replace("name", caster.getName())));
	    				((L2Attackable) npc).addDamageHate(caster, 0, 999);
	    				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, caster);
	    				break;
    				}
    			}
    		}
    	}
		return super.onSkillSee(npc, caster, skill, targets, isPet);
	}

    @Override
    public String onSpawn(L2Npc npc)
	{
    	if (equals(mobs1,npc.getNpcId()))
    	{
    		FastList<L2Playable> result = new FastList<L2Playable>();
    		Collection<L2Object> objs = npc.getKnownList().getKnownObjects().values();
    		for (L2Object obj : objs)
			{
				if (obj instanceof L2PcInstance || obj instanceof L2PetInstance)
				{
					if (Util.checkIfInRange(npc.getAggroRange(), npc, obj, true) && !((L2Character) obj).isDead())
						result.add((L2Playable) obj);
				}
			}
    		if (!result.isEmpty() && result.size() != 0)
    		{
    			Object[] characters = result.toArray();
    			for (Object obj : characters)
    			{
    	    		L2Playable target = (L2Playable) (obj instanceof L2PcInstance ? obj : ((L2Summon) obj).getOwner());
    	    		if (target.getActiveWeaponInstance() != null && !npc.isInCombat())
    	    		{
    	    			npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), text[0]));
    	    			switch (npc.getNpcId())
    	    			{
    	    				case 22124:
    	    				case 22126:
    	    				case 22127:
    	    				{
    	    					L2Skill skill = SkillTable.getInstance().getInfo(4589,8);
    	    	    			npc.setTarget(target);
    	    	    			npc.doCast(skill);
    	    	    			break;
    	    				}
    	    				default:
    	    				{
    	    					npc.setIsRunning(true);
    	    	    			((L2Attackable) npc).addDamageHate(target, 0, 999);
    	    	    			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
    	    	    			break;
    	    				}
    	    			}
    	    		}
    			}
    		}
    	}
		return super.onSpawn(npc);
	}

    @Override
    public String onSpellFinished(L2Npc npc, L2PcInstance player, L2Skill skill)
    {
    	if (equals(mobs1,npc.getNpcId()) && skill.getId() == 4589)
    	{
    		npc.setIsRunning(true);
    		((L2Attackable) npc).addDamageHate(player, 0, 999);
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
    	}
    	return super.onSpellFinished(npc, player, skill);
    }

    private boolean equals (int[] val1, int val2)
    {
    	for (int i=0;i<val1.length;i++)
    	{
    		if (val2 == val1[i])
    			return true;
    	}
    	return false;
    }
    public static void main(String[] args)
    {
        new Monastery(-1, "Monastery", "ai");
    }
}