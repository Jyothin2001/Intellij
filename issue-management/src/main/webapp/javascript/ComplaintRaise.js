//config: is an object that holds the configuration details.
var config = {
    //curl: is the URL endpoint to fetch the list of countries from the API.
    curl: 'https://api.countrystatecity.in/v1/countries',

    //ckey: is the encoded API key. This is used to authenticate your requests to the API
    ckey: 'Y1R2S2l2UnZDRFJHNlFBdWk3NGo1Y2M0ZnJka2V6aUhESVVJSUNjSA=='// Replace with your actual API key
};

//'YnZzWGQzT01Makp1VmVkTDBRMXlSN2xYSlpzbEhEUVdwZkozZUQxVw=='

async function fetchAPI(endpoint)
 {
    console.log(`Fetching data from endpoint: ${endpoint}`);
    try
     {
        const response = await fetch(endpoint, {
            headers: {
                'X-CSCAPI-KEY': config.ckey
            }
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        console.log('Received data:', data);
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        return null; // or handle error as needed
    }
}

async function loadCountries() {
    console.log('Loading countries...');
    const countries = await fetchAPI(config.curl);
    if (!countries) {
        console.error('Failed to load countries.');
        return;
    }
    const countrySelect = document.getElementById('countryName');
    countrySelect.innerHTML = '<option selected disabled>Choose Country</option>'; // Clear any existing options

    countries.forEach(country => {
        let option = document.createElement('option');
        option.value = country.name;  // Use country name instead of iso2
        option.textContent = country.name;
        countrySelect.appendChild(option);
    });

    console.log('Countries loaded:', countries);
}



async function loadStates(countryName)
{
    console.log(`Loading states for country name: ${countryName}`);

    const countries = await fetchAPI(config.curl);
    const country = countries.find(c => c.name === countryName);

    if (!country) {
        console.error(`Country not found: ${countryName}`);
        return;
    }
    const endpoint = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states`;
    const states = await fetchAPI(endpoint);
    if (!states) {
        console.error(`Failed to load states for country name ${countryName}.`);
        return;
    }
    const stateSelect = document.getElementById('state');
    stateSelect.innerHTML = '<option selected disabled>Choose State</option>'; // Clear any existing options

    states.forEach(state => {
        let option = document.createElement('option');
        option.value = state.name;  // Use state name instead of iso2
        option.textContent = state.name;
        stateSelect.appendChild(option);
    });

    console.log(`States loaded for country ${countryName}:`, states);
}





async function loadCities(stateName, countryName)
 {
    console.log(`Loading cities for state name: ${stateName} in country: ${countryName}`);

    const countries = await fetchAPI(config.curl);
    const country = countries.find(c => c.name === countryName);
    if (!country) {
        console.error(`Country not found: ${countryName}`);
        return;
    }
    const endpointStates = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states`;
    const states = await fetchAPI(endpointStates);
    const state = states.find(s => s.name === stateName);
    if (!state) {
        console.error(`State not found: ${stateName}`);
        return;
    }
    const endpointCities = `https://api.countrystatecity.in/v1/countries/${country.iso2}/states/${state.iso2}/cities`;
    const cities = await fetchAPI(endpointCities);
    if (!cities) {
        console.error(`Failed to load cities for state name ${stateName} in country ${countryName}.`);
        return;
    }
    const citySelect = document.getElementById('city');
    citySelect.innerHTML = '<option selected disabled>Choose City</option>'; // Clear any existing options

    cities.forEach(city => {
        let option = document.createElement('option');
        option.value = city.name;
        option.textContent = city.name;
        citySelect.appendChild(option);
    });

    console.log(`Cities loaded for state ${stateName}:`, cities);
}




// Call the function to fetch countries when the page loads
window.onload = loadCountries;

// Add event listener to load states when a country is selected
document.addEventListener('DOMContentLoaded', function () {
    const countrySelect = document.getElementById('countryName');
    countrySelect.addEventListener('change', function () {
        const countryName = this.value;
        if (countryName) {
            console.log(`Country selected: ${countryName}`);
            //it loads states related to country
            loadStates(countryName);
        }
    });

    // Add event listener to load cities when a state is selected
    const stateSelect = document.getElementById('state');
    stateSelect.addEventListener('change', function () {
        const stateName = this.value;
        const countryName = document.getElementById('countryName').value;
        if (stateName) {
            console.log(`State selected: ${stateName}`);
            loadCities(stateName, countryName);
        }
    });
});

