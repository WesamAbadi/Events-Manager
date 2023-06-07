
    // Function to fetch presenter options based on the selected organizer
    function fetchPresentersByOrganizer(organizerId) {
    fetch(`/api/presenters/organizer/${organizerId}/html`)
        .then(response => response.text())
        .then(html => {
            // Update the presenter options
            const presenterSelect = document.getElementById("presenter");
            presenterSelect.innerHTML = html;

            // Select the current presenter
            const currentPresenterId = document.getElementById("presenter").getAttribute("data-current-presenter");
            if (currentPresenterId) {
                presenterSelect.value = currentPresenterId;
            }
        })
        .catch(error => console.error(error));
}

    // Event listener for the organizer selection
    const organizerSelect = document.getElementById("organizer");
    organizerSelect.addEventListener("change", function() {
    const selectedOrganizerId = this.value;
    // Fetch presenter options based on the selected organizer
    fetchPresentersByOrganizer(selectedOrganizerId);
});

    // Trigger change event on page load to fetch initial presenter options
    organizerSelect.dispatchEvent(new Event('change'));

    // Function to handle event deletion
    function deleteEvent(event) {
    event.preventDefault();
    if (confirm("Are you sure you want to delete this event?")) {
    const form = event.target.closest('form');
    const url = form.getAttribute('action');
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', url, true);
    xhr.onload = function () {
    if (xhr.status === 204) {
    // Reload the page or update the event list
    location.reload();
} else {
    alert('Failed to delete the event. \n don\'t delete the original entries ￣へ￣');
}
};
    xhr.send();
}
}

