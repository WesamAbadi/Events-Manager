// Function to fetch presenter options based on the selected organizer
function fetchPresentersByOrganizer(organizerId) {
    fetch(`/api/presenters?organizerId=${organizerId}`)
        .then(response => response.json())
        .then(data => {
            // Update the presenter options
            const presenterSelect = document.getElementById("presenter");
            presenterSelect.innerHTML = "";

            // Create and append option elements
            let firstPresenterId = null; // Variable to store the first presenter ID

            for (const presenter of data) {
                const option = document.createElement("option");
                option.value = presenter.id;
                option.text = presenter.name;
                presenterSelect.appendChild(option);

                if (firstPresenterId === null) {
                    firstPresenterId = presenter.id; // Store the first presenter ID
                }
            }

            // Select the first presenter if available
            if (firstPresenterId) {
                presenterSelect.value = firstPresenterId;
            }
        })
        .catch(error => console.error(error));
}

// Rest of the code remains the same


// Event listener for the organizer selection
const organizerSelect = document.getElementById("organizer");
organizerSelect.addEventListener("change", function() {
    const selectedOrganizerId = this.value;
    // Fetch presenter options based on the selected organizer
    fetchPresentersByOrganizer(selectedOrganizerId);
});

// Trigger change event on page load to fetch initial presenter options
organizerSelect.dispatchEvent(new Event('change'));

// Validate presenter to be required
const presenterSelect = document.getElementById("presenter");
const createEventForm = document.getElementById("createEventForm");
createEventForm.addEventListener("submit", function(event) {
    if (presenterSelect.value === "") {
        event.preventDefault();
        alert("Presenter is required. Please select a presenter.");
    }
});
