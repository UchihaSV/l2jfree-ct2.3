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
package com.l2jfree.gameserver.model.skills.conditions;

import com.l2jfree.gameserver.model.skills.Env;

/**
*
* @author  janiii
*/
class ConditionTargetAbnormal extends Condition
{
	private final int _abnormalId;
	
	public ConditionTargetAbnormal(int abnormalId)
	{
		_abnormalId = abnormalId;
	}
	
	@Override
	boolean testImpl(Env env)
	{
		return (env.target.getAbnormalEffect() & _abnormalId) != 0;
	}
	
}
