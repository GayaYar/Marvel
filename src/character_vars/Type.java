package character_vars;

public enum Type {
    BLAST(1,2), COMBAT(2,3), SPEED(3,1), UNIVERSAL(0,null);

    private Integer id;
    private Integer against;

    Type(Integer id, Integer against) {
        this.id = id;
        this.against = against;
    }

    public Integer getId() {
        return id;
    }
    public boolean isAgainst(Type otherType) {
        return (against==null)? false: (against==otherType.getId());
    }
}
