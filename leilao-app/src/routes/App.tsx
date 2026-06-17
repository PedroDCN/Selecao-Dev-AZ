import { Navbar } from "@/components/navbar";
import { Outlet } from "react-router-dom";
import { Toaster } from "sonner";

export default function App() {
  return (
    <div className="bg-neutral-200/60 flex flex-col space-y-3 h-screen w-full">
      <Navbar />
      <main className="flex-1 overflow-y-auto w-full">
        <Outlet />
        <Toaster duration={4000} richColors />
      </main>
    </div>
  );
}
