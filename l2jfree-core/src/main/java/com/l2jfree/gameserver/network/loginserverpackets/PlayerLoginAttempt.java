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
package com.l2jfree.gameserver.network.loginserverpackets;

public class PlayerLoginAttempt extends LoginServerBasePacket
{
	private final String _ip;

	/**
	 * @param protocol
	 * @param decrypt
	 */
	public PlayerLoginAttempt(int protocol, byte[] decrypt)
	{
		super(protocol, decrypt);
		_ip = readS();
	}

	/**
	 * @return Returns the IP address.
	 */
	public String getIP()
	{
		return _ip;
	}
}
