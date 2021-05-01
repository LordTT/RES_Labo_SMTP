package mail;

/**
 * Class representing a mail to be sent to a smtp server
 */
public class Mail {
    private String from;
    private String[] to = new String[0];
    private String subject;
    private String message;

    /**
     * Gets the sender of the mail
     *
     * @return sender of the mail
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the sender of the mail
     *
     * @param from sender of the mail
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * get the recipients of the mail
     *
     * @return resipents of the mail
     */
    public String[] getTo() {
        return to;
    }

    /**
     * sets the recipients of the mail
     *
     * @param to recipients of the mail
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Gets the message of the mail
     *
     * @return message of the mail
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the mail
     *
     * @param message message of the mail
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
