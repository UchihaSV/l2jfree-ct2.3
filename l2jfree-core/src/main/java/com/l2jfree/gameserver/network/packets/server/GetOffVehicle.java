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
package com.l2jfree.gameserver.network.packets.server;

import com.l2jfree.gameserver.gameobjects.instance.L2BoatInstance;
import com.l2jfree.gameserver.gameobjects.instance.L2PcInstance;
import com.l2jfree.gameserver.network.packets.L2ServerPacket;

/**
 * @author Maktakien
 *
 */
public class GetOffVehicle extends L2ServerPacket
{
	private final int _x;
	private final int _y;
	private final int _z;
	private final L2PcInstance _activeChar;
	private final L2BoatInstance _boat;
	
	/**
	 * @param activeChar
	 * @param boat
	 * @param x
	 * @param y
	 * @param z
	 */
	public GetOffVehicle(L2PcInstance activeChar, L2BoatInstance boat, int x, int y, int z)
	{
		_activeChar = activeChar;
		_boat = boat;
		_x = x;
		_y = y;
		_z = z;
		
		if (_activeChar != null)
			_activeChar.setBoat(null);
	}
	
	/* (non-Javadoc)
	 * @see com.l2jfree.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		if (_boat == null || _activeChar == null)
			return;
		
		writeC(0x6f);
		writeD(_activeChar.getObjectId());
		writeD(_boat.getObjectId());
		writeD(_x);
		writeD(_y);
		writeD(_z);
		
	}
	
	/* (non-Javadoc)
	 * @see com.l2jfree.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[S] 5d GetOffVehicle";
	}
}
