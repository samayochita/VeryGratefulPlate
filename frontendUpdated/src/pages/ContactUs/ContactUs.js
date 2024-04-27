export const ContactUs = () => {
  return (
    <div>
      <h1> Contact Us</h1>
      <form>
        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" value="name" required />

        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" value="name" required />

        <label htmlFor="subject">Subject:</label>
        <input type="text" id="subject" name="subject" value="name" />

        <label htmlFor="message">Message:</label>
        <textarea id="message" name="message" rows="4" required></textarea>

        <button type="submit">Submit</button>
      </form>
    </div>
  );
};
