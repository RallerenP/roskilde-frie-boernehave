$(document).ready(async () => {
    const response = await fetch('http://localhost:8080/children');
    const obj = await response.json();
    $('#target').val(JSON.stringify(obj, undefined, 2))
});