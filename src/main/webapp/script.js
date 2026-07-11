// Shared behavior for the Student Registration app

document.addEventListener('DOMContentLoaded', function () {
	var toggle = document.getElementById('navToggle');
	var links = document.getElementById('navLinks');

	if (toggle && links) {
		toggle.addEventListener('click', function () {
			var isOpen = links.classList.toggle('open');
			toggle.classList.toggle('active', isOpen);
			toggle.setAttribute('aria-expanded', String(isOpen));
		});

		// Close the menu after tapping a link (nice on mobile)
		links.querySelectorAll('a').forEach(function (link) {
			link.addEventListener('click', function () {
				links.classList.remove('open');
				toggle.classList.remove('active');
				toggle.setAttribute('aria-expanded', 'false');
			});
		});
	}
});
