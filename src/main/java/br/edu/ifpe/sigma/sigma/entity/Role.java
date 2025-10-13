package br.edu.ifpe.sigma.sigma.entity;

public enum Role {
    ADMIN("Administrador"),
    SECRETARY("Secretário(a)"),
    PROFESSOR("Professor(a)"),
    STUDENT("Estudante"),
    TECHNICIAN("Técnico(a)");

    private final String description;

    Role(String description) {
        this.description = description;
    }


    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}