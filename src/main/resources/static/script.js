function getAllEvents() {
    fetch('/api/events')
        .then(response => response.json())
        .then(events => {
            const eventsContainer = document.getElementById('events-container');
            eventsContainer.innerHTML = '';

            events.forEach(event => {
                const eventDiv = document.createElement('div');
                eventDiv.classList.add('event');

                const eventName = document.createElement('h3');
                eventName.textContent = event.name;

                const eventDescription = document.createElement('p');
                eventDescription.textContent = event.description;

                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.addEventListener('click', () => deleteEvent(event.id));

                eventDiv.appendChild(eventName);
                eventDiv.appendChild(eventDescription);
                eventDiv.appendChild(deleteButton);

                eventsContainer.appendChild(eventDiv);
            });
        })
        .catch(error => console.error('Error:', error));
}

function addEvent() {
    const eventName = document.getElementById('event-name').value;
    const eventDescription = document.getElementById('event-description').value;

    const event = {
        name: eventName,
        description: eventDescription
    };

    fetch('/api/events', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(event)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            getAllEvents();
            clearForm();
        })
        .catch(error => console.error('Error:', error));
}

function deleteEvent(eventId) {
    fetch(`/api/events/${eventId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log('Event deleted');
                getAllEvents();
            }
        })
        .catch(error => console.error('Error:', error));
}

function clearForm() {
    document.getElementById('event-name').value = '';
    document.getElementById('event-description').value = '';
}

getAllEvents();
