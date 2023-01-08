/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{html,js,svelte,ts}',
    "./node_modules/flowbite-svelte/**/*.{html,js,svelte,ts}",
  ],
  theme: {
    extend: {
      colors: {
        github: {
          DEFAULT: "#24292F",
          dark: "#050708"
        },
        google: "#4285F4",
      }
    }
  },
  plugins: [
    require('flowbite/plugin')
  ],
  darkMode: 'class',
}
