package de.fuballer.mcendgame.main.context;

public class PierceContext {
    public static final ThreadLocal<PierceType> CURRENT = new ThreadLocal<>();

    public enum PierceType {
        KINETIC,
        PIERCE
    }
}
