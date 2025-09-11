import React, { useState, useEffect } from "react";

function ChatBox({ selectedChat }) {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");

  const token = localStorage.getItem("token");

  useEffect(() => {
    if (selectedChat && token) {
      fetch(`http://localhost:8080/api/chat/mensajes/${selectedChat.id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then(async (res) => {
          if (!res.ok) {
            const text = await res.text();
            throw new Error(`Error ${res.status}: ${text}`);
          }
          const text = await res.text();
          return text ? JSON.parse(text) : [];
        })
        .then((data) => setMessages(data))
        .catch((err) => console.error("Error cargando mensajes:", err));
    }
  }, [selectedChat, token]);

  const sendMessage = () => {
    if (!newMessage.trim() || !selectedChat || !token) return;

    fetch(`http://localhost:8080/api/chat/enviar`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        chatId: selectedChat.id,
        contenido: newMessage,
      }),
    })
      .then(async (res) => {
        if (!res.ok) {
          const text = await res.text();
          throw new Error(`Error ${res.status}: ${text}`);
        }
        const text = await res.text();
        return text ? JSON.parse(text) : null;
      })
      .then((msg) => {
        if (msg) {
          setMessages((prev) => [...prev, msg]);
        }
        setNewMessage("");
      })
      .catch((err) => console.error("Error enviando mensaje:", err));
  };

  if (!selectedChat) {
    return <div className="p-4">Selecciona un chat para empezar a conversar</div>;
  }

  return (
    <div className="flex flex-col h-full border rounded-lg shadow bg-white">
      <div className="p-3 border-b font-semibold">
        Chat con {selectedChat.destinatarioNombre || selectedChat.remitenteNombre}
      </div>

      <div className="flex-1 p-3 overflow-y-auto space-y-2">
        {messages.map((msg) => (
          <div
            key={msg.id}
            className={`p-2 rounded-lg max-w-[70%] ${
              msg.esPropio
                ? "bg-blue-500 text-white self-end ml-auto"
                : "bg-gray-200 text-black self-start"
            }`}
          >
            {msg.contenido}
          </div>
        ))}
      </div>

      <div className="p-3 border-t flex gap-2">
        <input
          type="text"
          placeholder="Escribe un mensaje..."
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          className="flex-1 border rounded p-2"
        />
        <button
          onClick={sendMessage}
          className="px-4 py-2 bg-blue-500 text-white rounded"
        >
          Enviar
        </button>
      </div>
    </div>
  );
}

export default ChatBox;



