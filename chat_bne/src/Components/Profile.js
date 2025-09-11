import React from "react";

function Profile({ profile, chats, onSelectChat }) {
  if (!profile) return <div className="p-4">Cargando perfil...</div>;

  return (
    <div className="border rounded-lg shadow bg-white p-4 h-full">
      <div className="flex items-center gap-3 mb-4">
        <div className="w-12 h-12 rounded-full bg-gray-300 flex items-center justify-center">
          {profile.name[0].toUpperCase()}
        </div>
        <div>
          <h2 className="font-bold">{profile.name}</h2>
          <p className="text-sm text-gray-500">{profile.email}</p>
        </div>
      </div>

      <h3 className="font-semibold mb-2">Chats disponibles</h3>
      <ul className="space-y-2">
        {chats.map((chat) => (
          <li key={chat.id}>
            <button
              onClick={() => onSelectChat(chat)}
              className="w-full text-left p-2 rounded bg-gray-100 hover:bg-gray-200"
            >
              {chat.name}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Profile;


