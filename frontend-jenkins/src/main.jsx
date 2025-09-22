import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import PetManager from "./components/PetManager";   // import PetManager

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PetManager />   {/* Render PetManager directly */}
  </StrictMode>,
);
