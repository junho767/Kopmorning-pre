document.addEventListener('DOMContentLoaded', function() {
    const events = document.querySelectorAll('.event');
    const details = document.querySelectorAll('.details');

    events.forEach(event => {
        event.addEventListener('mouseenter', () => {
            details.forEach(detail => detail.classList.remove('active'));
            const detailId = event.getAttribute('data-details');
            document.getElementById(detailId).classList.add('active');
        });
    });
    document.querySelectorAll('.league').forEach(item => {
        item.addEventListener('click', event => {
            const years = event.target.getAttribute('data-years');
            const championYears = document.getElementById('champion-years');
            championYears.innerHTML = years.split(',').map(year => `<span>${year.trim()}</span>`).join('');
        });
    });
});