import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
} from "@/components/ui/navigation-menu";
import { NavLink } from "react-router-dom";

export function Navbar() {
  return (
    <NavigationMenu className="h-8 flex-0 max-w-full pt-4">
      <NavigationMenuList className="gap-5">
        <NavigationMenuItem>
          <NavLink
            to="/empresas"
            className={({ isActive }) => {
              return isActive ? "text-emerald-700" : "text-black";
            }}
          >
            <NavigationMenuLink className="text-lg font-semibold">
              Empresas
            </NavigationMenuLink>
          </NavLink>
        </NavigationMenuItem>
        <NavigationMenuItem>
          <NavLink
            to="/leiloes"
            className={({ isActive }) => {
              return isActive ? "text-emerald-800" : "text-black";
            }}
          >
            <NavigationMenuLink className="text-lg font-semibold">
              Leilões
            </NavigationMenuLink>
          </NavLink>
        </NavigationMenuItem>
        <NavigationMenuItem>
          <NavLink
            to="/unidades"
            className={({ isActive }) => {
              return isActive ? "text-emerald-800" : "text-black";
            }}
          >
            <NavigationMenuLink className="text-lg font-semibold">
              Unidades
            </NavigationMenuLink>
          </NavLink>
        </NavigationMenuItem>
      </NavigationMenuList>
    </NavigationMenu>
  );
}
