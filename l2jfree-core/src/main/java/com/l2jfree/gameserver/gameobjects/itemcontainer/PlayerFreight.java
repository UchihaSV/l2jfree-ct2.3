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
package com.l2jfree.gameserver.gameobjects.itemcontainer;

import com.l2jfree.Config;
import com.l2jfree.gameserver.gameobjects.L2Player;
import com.l2jfree.gameserver.model.items.L2ItemInstance;
import com.l2jfree.gameserver.model.items.L2ItemInstance.ItemLocation;
import com.l2jfree.util.ArrayBunch;

public class PlayerFreight extends ItemContainer
{
	private final L2Player _owner; // This is the L2Player that owns this Freight;
	private int _activeLocationId;
	private int _tempOwnerId = 0;
	
	public PlayerFreight(L2Player owner)
	{
		_owner = owner;
	}
	
	@Override
	public String getName()
	{
		return "Freight";
	}
	
	@Override
	public L2Player getOwner()
	{
		return _owner;
	}
	
	@Override
	public ItemLocation getBaseLocation()
	{
		return ItemLocation.FREIGHT;
	}
	
	public void setActiveLocation(int locationId)
	{
		_activeLocationId = locationId;
	}
	
	/**
	 * Returns the quantity of items in the inventory
	 * 
	 * @return int
	 */
	@Override
	public int getSize()
	{
		int size = 0;
		for (L2ItemInstance item : _items)
		{
			if (item.getLocationSlot() == 0 || _activeLocationId == 0 || item.getLocationSlot() == _activeLocationId)
				size++;
		}
		return size;
	}
	
	/**
	 * Returns the list of items in inventory
	 * 
	 * @return L2ItemInstance : items in inventory
	 */
	@Override
	public L2ItemInstance[] getItems()
	{
		ArrayBunch<L2ItemInstance> list = new ArrayBunch<L2ItemInstance>();
		for (L2ItemInstance item : _items)
		{
			if (item.getLocationSlot() == 0 || item.getLocationSlot() == _activeLocationId)
				list.add(item);
		}
		
		return list.moveToArray(new L2ItemInstance[list.size()]);
	}
	
	/**
	 * Returns the item from inventory by using its <B>itemId</B>
	 * 
	 * @param itemId : int designating the ID of the item
	 * @return L2ItemInstance designating the item or null if not found in
	 *         inventory
	 */
	@Override
	public L2ItemInstance getItemByItemId(int itemId)
	{
		for (L2ItemInstance item : _items)
		{
			if ((item.getItemId() == itemId)
					&& (item.getLocationSlot() == 0 || _activeLocationId == 0 || item.getLocationSlot() == _activeLocationId))
				return item;
		}
		
		return null;
	}
	
	/**
	 * Adds item to PcFreight for further adjustments.
	 * 
	 * @param item : L2ItemInstance to be added from inventory
	 */
	@Override
	protected void addItem(L2ItemInstance item)
	{
		super.addItem(item);
		if (_activeLocationId > 0)
			item.setLocation(item.getLocation(), _activeLocationId);
	}
	
	/**
	 * Get back items in PcFreight from database
	 */
	@Override
	public void restore()
	{
		int locationId = _activeLocationId;
		_activeLocationId = 0;
		super.restore();
		_activeLocationId = locationId;
	}
	
	@Override
	public boolean validateCapacity(int slots)
	{
		int cap = ((_owner == null) ? Config.FREIGHT_SLOTS : _owner.getFreightLimit());
		return ((getSize() + slots) <= cap);
	}
	
	@Override
	public int getOwnerId()
	{
		if (_owner == null)
			return _tempOwnerId;
		return super.getOwnerId();
	}
	
	/**
	 * This provides support to load a new PcFreight without owner so that transactions can be done
	 */
	public void doQuickRestore(int val)
	{
		_tempOwnerId = val;
		restore();
	}
}
