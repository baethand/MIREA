const header = document.getElementById('header');

window.addEventListener('scroll', () => {
  const isAtTop = window.scrollY === 0;
  const isHovering = header.matches(':hover');

  if (isAtTop || isHovering) {
    header.classList.remove('header-transparent');
  } else {
    header.classList.add('header-transparent');
  }
});

header.addEventListener('mouseenter', () => {
  header.classList.remove('header-transparent');
});

header.addEventListener('mouseleave', () => {
  if (window.scrollY !== 0) {
    header.classList.add('header-transparent');
  }
});
