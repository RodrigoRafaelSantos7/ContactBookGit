package contactBook;

public class ContactBook {
    static final int DEFAULT_SIZE = 100;

    private int counter;
    private Contact[] contacts;
    private int currentContact;

    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
        currentContact = -1;
    }

    //Pre: name != null
    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    /**
     * Returns true if the contact book has a contact with the given phone number
     * @param phone int
     * @return boolean if the contact book has a contact with the given phone number
     */
    public boolean hasPhone(int phone) {
        boolean found = false;

        for (int i = 0; i < counter; i++) {
            Contact contact = contacts[i];
            if (contact.getPhone() == phone) {
                found = true;
                break;
            }
        }

        return found;
    }

    public int getNumberOfContacts() {
        return counter;
    }

    //Pre: name!= null && !hasContact(name)
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    //Pre: name != null && hasContact(name)
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for (int i = index; i < counter; i++)
            contacts[i] = contacts[i + 1];
        counter--;
    }

    //Pre: name != null && hasContact(name)
    public int getPhone(String name) {
        return contacts[searchIndex(name)].getPhone();
    }

    //Pre: name != null && hasContact(name)
    public String getEmail(String name) {
        return contacts[searchIndex(name)].getEmail();
    }

    //Pre: name != null && hasContact(name)
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name)].setPhone(phone);
    }

    //Pre: name != null && hasContact(name)
    public void setEmail(String name, String email) {
        contacts[searchIndex(name)].setEmail(email);
    }

    private int searchIndex(String name) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < counter && !found)
            if (contacts[i].getName().equals(name))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    private void resize() {
        Contact tmp[] = new Contact[2 * contacts.length];
        for (int i = 0; i < counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }

    public void initializeIterator() {
        currentContact = 0;
    }

    public boolean hasNext() {
        return (currentContact >= 0) && (currentContact < counter);
    }

    //Pre: hasNext()
    public Contact next() {
        return contacts[currentContact++];
    }

    /**
     * Returns the name of the contact with the given phone number
     * @Pre: phone != null && hasPhone(phone)
     * @param phone int
     * @return a string which is the name of the contact with the given number
     */
    public String getNameByNumber(int phone) {
        String name = null;

        for (Contact c : contacts) {
            if (c.getPhone() == phone) {
                name = c.getName();
                break;
            }
        }
        return name;
    }

    public boolean equalNumber(Contact contact) {
        for (Contact c : contacts) {
            if (c != null && !c.equals(contact) && c.getPhone() == contact.getPhone())
                return true;
        }
        return false;
    }
}
