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
package com.l2jfree.gameserver.handler.admincommandhandlers;

import com.l2jfree.Config;
import com.l2jfree.gameserver.geodata.GeoClient;
import com.l2jfree.gameserver.handler.IAdminCommandHandler;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;

/**
 *
 * @author  -Nemesiss-
 */
public class AdminGeodata implements IAdminCommandHandler
{
	private static final String[]	ADMIN_COMMANDS	=
													{
			"admin_geo_z",
			"admin_geo_type",
			"admin_geo_nswe",
			"admin_geo_los",
			"admin_geo_position",
			"admin_geo_bug",
			"admin_geo_load",
			"admin_geo_unload"						};
	private static final int		REQUIRED_LEVEL	= Config.GM_MIN;

	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		if (!Config.ALT_PRIVILEGES_ADMIN)
			if (!(checkLevel(activeChar.getAccessLevel()) && activeChar.isGM()))
				return false;

		if (!Config.GEODATA)
		{
			activeChar.sendMessage("Geo Engine is turned off!");
			return true;
		}

		if (command.equals("admin_geo_z"))
			activeChar.sendMessage("GeoEngine: Geo_Z = " + GeoClient.getInstance().getHeight(activeChar.getX(), activeChar.getY(), activeChar.getZ())
					+ " Loc_Z = " + activeChar.getZ());
		else if (command.equals("admin_geo_type"))
		{
			short type = GeoClient.getInstance().getType(activeChar.getX(), activeChar.getY());
			activeChar.sendMessage("GeoEngine: Geo_Type = " + type);
			int height = GeoClient.getInstance().getHeight(activeChar.getX(), activeChar.getY(), activeChar.getZ());
			activeChar.sendMessage("GeoEngine: height = " + height);
		}
		else if (command.equals("admin_geo_nswe"))
		{
			String result = "";
			short nswe = GeoClient.getInstance().getNSWE(activeChar.getX(), activeChar.getY(), activeChar.getZ());
			if ((nswe & 8) == 0)
				result += " N";
			if ((nswe & 4) == 0)
				result += " S";
			if ((nswe & 2) == 0)
				result += " W";
			if ((nswe & 1) == 0)
				result += " E";
			activeChar.sendMessage("GeoEngine: Geo_NSWE -> " + nswe + "->" + result);
		}
		else if (command.equals("admin_geo_los"))
		{
			if (activeChar.getTarget() != null)
			{
				if (GeoClient.getInstance().canSeeTarget(activeChar, activeChar.getTarget()))
					activeChar.sendMessage("GeoEngine: Can see target");
				else
					activeChar.sendMessage("GeoEngine: Can't see target");

			}
			else
				activeChar.sendMessage("No target!");
		}
		else if (command.equals("admin_geo_position"))
		{
			activeChar.sendMessage("GeoEngine: Your current position: ");
			activeChar.sendMessage(".... world coords: x: " + activeChar.getX() + " y: " + activeChar.getY() + " z: " + activeChar.getZ());
			//activeChar.sendMessage(".... geo position: " + GeoClient.getInstance() .geoPosition(activeChar.getX(), activeChar.getY()));
		}
		else if (command.startsWith("admin_geo_load"))
		{
			/*String[] v = command.substring(15).split(" ");
			if (v.length != 2)
				activeChar.sendMessage("Usage: //geo_load <regionX> <regionY>");
			else
			{
				try
				{
					byte rx = Byte.parseByte(v[0]);
					byte ry = Byte.parseByte(v[1]);

					boolean result = GeoClient.loadGeoEngineFile(rx, ry);

					if (result)
						activeChar.sendMessage("GeoEngine: File for region [" + rx + "," + ry + "] loaded successfully");
					else
						activeChar.sendMessage("GeoEngine: File for region [" + rx + "," + ry + "] couldn't be loaded");
				}
				catch (Exception e)
				{
					activeChar.sendMessage("Usage: //geo_load <regionX> <regionY>");
				}
			}*/
		}
		else if (command.startsWith("admin_geo_unload"))
		{
			/*String[] v = command.substring(17).split(" ");
			if (v.length != 2)
				activeChar.sendMessage("Usage: //geo_unload <regionX> <regionY>");
			else
			{
				try
				{
					byte rx = Byte.parseByte(v[0]);
					byte ry = Byte.parseByte(v[1]);

					boolean result = GeoClient.unloadGeoEngine(rx, ry);
					if (result)
						activeChar.sendMessage("GeoEngine: File for region [" + rx + "," + ry + "] unloaded.");
					else
						activeChar.sendMessage("GeoEngine: File for region [" + rx + "," + ry + "] couldn't be unloaded");
				}
				catch (Exception e)
				{
					activeChar.sendMessage("You have to write numbers of regions <regionX> <regionY>");
				}
			}*/
		}
		else if (command.startsWith("admin_geo_bug"))
		{
			/*try
			{
				String comment = command.substring(14);
				GeoClient.getInstance().addGeoEngineBug(activeChar, comment);
			}
			catch (StringIndexOutOfBoundsException e)
			{
				activeChar.sendMessage("Usage: //geo_bug <your comments here>");
			}*/
		}
		return true;
	}

	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}

	private boolean checkLevel(int level)
	{
		return (level >= REQUIRED_LEVEL);
	}

}
