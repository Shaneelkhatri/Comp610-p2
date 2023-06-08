package assignment.pkg1;

public class Card {
    private int code;

    private int value;
    private String name;
    private int suite;
    private String suiteName;


    public Card(int value, int suite) {
        this.code = value + ((suite-1)*13);

        this.suite = suite;
        switch (suite) {
            case 1:
                setSuiteName("Hearts");
                break;
            case 2:
                setSuiteName("Diamonds");
                break;
            case 3:
                setSuiteName("Clubs");
                break;
            case 4:
                setSuiteName("Spades");
                break;
            default:
                break;
        }


        switch (value) {
            case 1:
                setName("Ace");
                setValue(11);
                break;
            case 2:
                setName("Two");
                setValue(2);
                break;
            case 3:
                setName("Three");
                setValue(3);
                break;
            case 4:
                setName("Four");
                setValue(4);
                break;
            case 5:
                setName("Five");
                setValue(5);
                break;
            case 6:
                setName("Six");
                setValue(6);
                break;
            case 7:
                setName("Seven");
                setValue(7);
                break;
            case 8:
                setName("Eight");
                setValue(8);
                break;
            case 9:
                setName("Nine");
                setValue(9);
                break;
            case 10:
                setName("Ten");
                setValue(10);
                break;
            case 11:
                setName("Jack");
                setValue(10);
                break;
            case 12:
                setName("Queen");
                setValue(10);
                break;
            case 13:
                setName("King");
                setValue(10);
                break;
            default:
                break;

        }
    }




    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
}
