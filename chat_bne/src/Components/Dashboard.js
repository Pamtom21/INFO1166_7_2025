import React, { useState, useEffect } from "react";
import Profile from "./Profile";
import ChatBox from "./Chat";

function Dashboard() {
  const [profile, setProfile] = useState(null);
  const [chats, setChats] = useState([]);
  const [selectedChat, setSelectedChat] = useState(null);

  // Cargar perfil y chats al montar
  useEffect(() => {
    fetch("http://localhost:4000/api/profile", { credentials: "include" })
      .then((res) => res.json())
      .then((data) => setProfile(data))
      .catch((err) => console.error("Error cargando perfil:", err));

    fetch("http://localhost:4000/api/chats", { credentials: "include" })
      .then((res) => res.json())
      .then((data) => setChats(data))
      .catch((err) => console.error("Error cargando chats:", err));
  }, []);

  return (
    <div className="grid grid-cols-3 gap-4 h-screen p-4 bg-gray-50">
      <Profile profile={profile} chats={chats} onSelectChat={setSelectedChat} />
      <div className="col-span-2">
        <ChatBox selectedChat={selectedChat} />
      </div>
    </div>
  );
}

export default Dashboard;

