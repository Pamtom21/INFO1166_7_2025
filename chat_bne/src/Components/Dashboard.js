import React, { useState, useEffect } from "react";
import Profile from "./Profile";
import ChatBox from "./Chat";

function Dashboard() {
  const [profile, setProfile] = useState(null);
  const [chats, setChats] = useState([]);
  const [selectedChat, setSelectedChat] = useState(null);
  const [perfilesDisponibles, setPerfilesDisponibles] = useState([]);

  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token) return;

    // -------------------------------
    // 🔹 Cargar perfil
    // -------------------------------
    fetch("http://localhost:8080/api/usuario/profile", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setProfile(data);

        // Cargar perfiles del otro tipo
        const endpoint =
          data.tipo === "EMPRESA"
            ? "/api/usuario/postulantes"
            : "/api/usuario/empresas";

        fetch(`http://localhost:8080${endpoint}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
          .then((res) => res.json())
          .then((perfiles) => setPerfilesDisponibles(perfiles))
          .catch((err) => console.error("Error cargando perfiles:", err));
      })
      .catch((err) => console.error("Error cargando perfil:", err));

    // -------------------------------
    // 🔹 Cargar chats
    // -------------------------------
    fetch("http://localhost:8080/api/chat/mis-chats", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        if (!Array.isArray(data)) {
          console.error("Error: los chats no son un array válido", data);
          setChats([]);
          return;
        }

        // Filtrar duplicados y chats vacíos
        const uniqueChats = data
          .filter((chat) => chat && chat.id) // eliminar nulos o vacíos
          .reduce((acc, chat) => {
            if (!acc.find((c) => c.id === chat.id)) acc.push(chat);
            return acc;
          }, []);

        setChats(uniqueChats);
      })
      .catch((err) => console.error("Error cargando chats:", err));
  }, [token]);

  return (
    <div className="grid grid-cols-3 gap-4 h-screen p-4 bg-gray-50">
      <Profile
        profile={profile}
        chats={chats}
        onSelectChat={setSelectedChat}
        perfilesDisponibles={perfilesDisponibles}
      />
      <div className="col-span-2">
        <ChatBox selectedChat={selectedChat} />
      </div>
    </div>
  );
}

export default Dashboard;
