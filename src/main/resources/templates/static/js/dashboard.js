// Dashboard functionality
$(document).ready(function() {
    // Initialize tooltips
    $('[data-toggle="tooltip"]').tooltip();
    
    // Conversation list click handler
    $('#conversation-list-container .list-group-item').click(function(e) {
        e.preventDefault();
        
        // Remove active class from all items
        $('#conversation-list-container .list-group-item').removeClass('active');
        
        // Add active class to clicked item
        $(this).addClass('active');
        
        // Update chat header
        const companyName = $(this).find('h6').text();
        $('#chat-partner-name').text(companyName);
        $('#current-conversation').text(companyName);
        
        // Simulate loading messages
        simulateConversation(companyName);
    });
    
    // Message form submission
    $('#message-form').submit(function(e) {
        e.preventDefault();
        
        const messageInput = $('#message-input');
        const message = messageInput.val().trim();
        
        if (message) {
            sendMessage(message);
            messageInput.val('');
        }
    });
    
    function simulateConversation(companyName) {
        // Clear current messages
        $('#chat-messages').empty();
        
        // Add sample messages based on company
        const messages = [
            {
                type: 'received',
                content: `¡Hola! Nos ha gustado mucho tu perfil para la posición en ${companyName}.`,
                time: '10:30 AM'
            },
            {
                type: 'sent', 
                content: '¡Gracias! Me interesa mucho la oportunidad. ¿Podrían enviarme más información?',
                time: '10:32 AM'
            },
            {
                type: 'received',
                content: 'Claro que sí. Te enviaremos los detalles por email. ¿Cuál es tu disponibilidad?',
                time: '10:35 AM'
            }
        ];
        
        messages.forEach(msg => {
            addMessage(msg.type, msg.content, msg.time);
        });
    }
    
    function addMessage(type, content, time) {
        const messageHtml = `
            <div class="message ${type}">
                <div class="message-content">
                    <p>${content}</p>
                </div>
                <div class="message-time">${time}</div>
            </div>
        `;
        
        $('#chat-messages').append(messageHtml);
        $('#chat-messages').scrollTop($('#chat-messages')[0].scrollHeight);
    }
    
    function sendMessage(content) {
        const time = new Date().toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
        addMessage('sent', content, time);
        
        // Simulate response after 1-2 seconds
        setTimeout(() => {
            const responses = [
                '¡Entendido! Te contactaremos pronto.',
                'Gracias por la información.',
                'Perfecto, lo tendremos en cuenta.',
                '¿Alguna otra pregunta?'
            ];
            
            const randomResponse = responses[Math.floor(Math.random() * responses.length)];
            addMessage('received', randomResponse, new Date().toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'}));
        }, 1000 + Math.random() * 1000);
    }
});