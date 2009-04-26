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
package com.l2jfree.gameserver.network.clientpackets;

import com.l2jfree.gameserver.model.L2Object;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.actor.instance.L2StaticObjectInstance;
import com.l2jfree.gameserver.network.serverpackets.ActionFailed;

/**
 * This class ...
 * 
 * @version $Revision: 1.1.4.3 $ $Date: 2005/03/27 15:29:30 $
 */
public class ChangeWaitType2 extends L2GameClientPacket
{
	private static final String _C__1D_CHANGEWAITTYPE2 = "[C] 1D ChangeWaitType2";

	private boolean _typeStand;
	
	/**
	 * packet type id 0x1d
	 * 
	 * sample
	 * 
	 * 1d
	 * 01 00 00 00 // type (0 = sit, 1 = stand)
	 * 
	 * format:		cd
	 * @param decrypt
	 */
	@Override
	protected void readImpl()
	{
		_typeStand = (readD() == 1);
	}

	@Override
	protected void runImpl()
	{
		//WARNING: not used in CT2?
		L2PcInstance player = getClient().getActiveChar();
		if (player == null) return;

		if (player.isOutOfControl() || player.isTryingToSitOrStandup()
				|| player.getMountType() != 0)
		{
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		L2Object target = player.getTarget();
		if (target instanceof L2StaticObjectInstance && !player.isSitting())
		{
			if (!((L2StaticObjectInstance) target).onSit(player))
				player.sendMessage("Sitting on throne has failed.");
			else
				return;
		}

		if (_typeStand)
		{
			player.resetThrone();
			player.standUp(false); // User requested - Checks if animation already running.
		}
		else
			player.sitDown(false); // User requested - Checks if animation already running.
	}

	/* (non-Javadoc)
	 * @see com.l2jfree.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__1D_CHANGEWAITTYPE2;
	}
}
