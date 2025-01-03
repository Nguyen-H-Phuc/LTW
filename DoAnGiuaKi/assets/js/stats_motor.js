// Script for rendering the revenue chart using Chart.js
const ctx = document.getElementById('revenueChart').getContext('2d');
const revenueChart = new Chart(ctx, {
    type: 'line', // Change this to other types if needed
    data: {
        labels: ['Honda Wave RSX', 'Honda Vision', 'Honda SH', 'Air Blade 125cc', 'Yamaha Grande', 'Suzuki Hayate'], // Example labels
        datasets: [{
            label: 'Doanh thu',
            data: [12, 10, 8, 6, 4, 2], // Example data
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
