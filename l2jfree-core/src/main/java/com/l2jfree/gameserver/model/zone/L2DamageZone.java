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
package com.l2jfree.gameserver.model.zone;

import com.l2jfree.gameserver.model.L2Character;

public class L2DamageZone extends L2DangerZone
{
	@Override
	protected void checkForDamage(L2Character character)
	{
		if (_hpDamage > 0)
			//do never use null as the second argument!
			character.reduceCurrentHp(_hpDamage, character);
		if (_mpDamage > 0)
			character.reduceCurrentMp(_mpDamage);
	}
}
