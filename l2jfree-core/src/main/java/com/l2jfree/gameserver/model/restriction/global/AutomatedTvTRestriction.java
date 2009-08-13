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
package com.l2jfree.gameserver.model.restriction.global;

import com.l2jfree.Config;
import com.l2jfree.gameserver.handler.IItemHandler;
import com.l2jfree.gameserver.model.L2ItemInstance;
import com.l2jfree.gameserver.model.actor.L2Playable;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.entity.events.AutomatedTvT;
import com.l2jfree.gameserver.network.SystemMessageId;
import com.l2jfree.gameserver.network.serverpackets.ActionFailed;

/**
 * @author NB4L1
 */
public final class AutomatedTvTRestriction extends AbstractRestriction// extends AbstractFunEventRestriction
{
	private static final class SingletonHolder
	{
		private static final AutomatedTvTRestriction INSTANCE = new AutomatedTvTRestriction();
	}
	
	public static AutomatedTvTRestriction getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private AutomatedTvTRestriction()
	{
	}
	
	@Override
	public boolean isRestricted(L2PcInstance activeChar, Class<? extends GlobalRestriction> callingRestriction)
	{
		if (AutomatedTvT.isReged(activeChar) || AutomatedTvT.isPlaying(activeChar))
		{
			activeChar.sendMessage("You are participating in a fun event!");
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canRequestRevive(L2PcInstance activeChar)
	{
		if (!Config.AUTO_TVT_REVIVE_SELF && AutomatedTvT.isPlaying(activeChar))
			return false;
		
		return true;
	}
	
	@Override
	public boolean canUseItemHandler(Class<? extends IItemHandler> clazz, int itemId, L2Playable activeChar,
		L2ItemInstance item, L2PcInstance player)
	{
		if (player != null && AutomatedTvT.isPlaying(player) && !AutomatedTvT.canUse(itemId))
		{
			player.sendPacket(SystemMessageId.NOT_WORKING_PLEASE_TRY_AGAIN_LATER);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean canBeInsidePeaceZone(L2PcInstance activeChar, L2PcInstance target)
	{
		if (AutomatedTvT.isPlaying(activeChar) && AutomatedTvT.isPlaying(target))
			return false;
		
		return true;
	}
	
	@Override
	public void playerLoggedIn(L2PcInstance activeChar)
	{
		AutomatedTvT.getInstance().addDisconnected(activeChar);
	}
	
	@Override
	public void playerDisconnected(L2PcInstance activeChar)
	{
		AutomatedTvT.getInstance().onDisconnection(activeChar);
	}
}
