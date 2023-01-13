package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Singleton class which defines a menu that contains items that can be ordered from.
 */
public class Menu {
    /**
     * the items that have been registered with the Menu.
     */
    private List<MenuItem> menuItems;

    /**
     * singleton instance of the menu.
     */
    private static final Menu instance = new Menu();

    private Menu() {
        this.menuItems = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the menu.
     *
     * @return singleton instance
     */
    public static Menu getInstance() {
        return instance;
    }

    /**
     * Returns the items that have been registered with the Menu.
     *
     * @return the items on the menu.
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(menuItems);
    }

    /**
     * Registers a menu item with this menu.
     *
     * @param item a menu item to be registered to the menu
     */
    public void registerMenuItem(MenuItem item) {
        boolean registedBefore = false;
        for (MenuItem menuItem : menuItems) {
            if (Objects.equals(menuItem, item)) {
                registedBefore = true;
                break;
            }
        }

        if (!registedBefore) {
            menuItems.add(item);
        }
    }

    /**
     * Returns a menu item from the list of loaded menu items
     *
     * @param index the index of the item in the list
     * @return The item that has been found.
     * @throws IndexOutOfBoundsException if index does not exist or array is null
     */
    public MenuItem get(int index) throws IndexOutOfBoundsException {
        if (menuItems == null || index > menuItems.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return menuItems.get(index);
    }

    /**
     * Removes all loaded items from the Menu such that getItems() will return a list of size 0.
     */
    public void clear() {
        menuItems.clear();
    }
}
