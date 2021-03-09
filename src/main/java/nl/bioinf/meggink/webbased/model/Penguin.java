package nl.bioinf.meggink.webbased.model;

public class Penguin {
    private String scientificName;
    private String englishName;
    private String dutchName;
    private int length;
    private int weight;
    private int age;
    private String picture;

    public Penguin(String scientificName, String englishName, String dutchName, int length, int weight, int age, String picture) {
        this.scientificName = scientificName;
        this.englishName = englishName;
        this.dutchName = dutchName;
        this.length = length;
        this.weight = weight;
        this.age = age;
        this.picture = picture;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getSpecies(){
        String[] elements = scientificName.split(" ");
        return elements[0];
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getDutchName() {
        return dutchName;
    }

    public int getLength() {
        return length;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "scientificName='" + scientificName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", dutchName='" + dutchName + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}
