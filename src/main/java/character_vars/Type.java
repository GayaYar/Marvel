package character_vars;

public enum Type {
    BLAST(1,2), COMBAT(2,3), SPEED(3,1), UNIVERSAL(0,null);

    //my id
    private final Integer id;
    //the id of the type I have advantage against
    private final Integer against;

    Type(Integer id, Integer against) {
        this.id = id;
        this.against = against;
    }

    public Integer getId() {
        return id;
    }

    /**
     *
     * @param otherType - a Type instance to check
     * @return if I have advantage against the given type ("otherType")
     */
    public boolean isAgainst(Type otherType) {
        return (against!=null)&&(against.equals(otherType.getId()));
    }
}
