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
package net.sf.l2j.gameserver.serverpackets;

import java.util.logging.Logger;

import net.sf.l2j.gameserver.model.L2Clan;

/**
 * 
 *
 * sample
 * 0000: 9c c10c0000 48 00 61 00 6d 00 62 00 75 00 72    .....H.a.m.b.u.r
 * 0010: 00 67 00 00 00 00000000 00000000 00000000 00000000 00000000 00000000 
 * 00 00 
 * 00000000                                           ...
 
 * format   dSS C4
 * format   dSddddddSd C5 
 * 
 * @version $Revision: 1.3.2.1.2.3 $ $Date: 2005/03/27 15:29:57 $
 */
public class PledgeInfo extends ServerBasePacket
{
	private static final String _S__9C_PLEDGEINFO = "[S] 9C PledgeInfo";
	private L2Clan _clan;
    private static Logger _log = Logger.getLogger(ServerBasePacket.class.getName());
    
    public PledgeInfo(L2Clan clan)
	{
		_clan = clan;
	}	
	
	final void runImpl()
	{
		// no long-running tasks
	}
	
	final void writeImpl()
	{
		writeC(0x83);
		writeD(_clan.getClanId());
		writeS(_clan.getName());
        writeD(_clan.getAllyId());
        writeD(_clan.getAllyId());
        writeD(_clan.getAllyId());
        writeD(_clan.getAllyId());
        writeD(_clan.getAllyId());
        writeD(_clan.getAllyId());
		writeS(_clan.getAllyName());
        writeD(0);
	}
	
	/* (non-Javadoc)
	 * @see net.sf.l2j.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	public String getType()
	{
		return _S__9C_PLEDGEINFO;
	}

}
