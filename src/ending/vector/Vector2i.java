package ending.vector;

/**
 * A class that manages 2D vectors of int values.
 *
 * @author Nick
 */
public class Vector2i {

    /**
     * The x value of this Vector2i.
     */
    public final int x;

    /**
     * The y value of this Vector2i.
     */
    public final int y;

    /**
     * The Zero Vector2i, defined as (0, 0).
     */
    public static final Vector2i ZERO = new Vector2i(0, 0);
    
    /**
     * Constructs a new Vector2i, with both x and y set to 0.
     */
    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a new Vector2i based off x and y parameters.
     * @param x the x value to set this Vector2i to.
     * @param y the y value to set this Vector2i to.
     */
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector2i based off of another Vector2i.
     * @param v the Vector2i to have this Vector2i constructed from.
     */
    public Vector2i(Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Constructs a new Vector2i based off of JSFML's Vector2i class.
     * @param v
     */
    public Vector2i(org.jsfml.system.Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Converts a JSFML Vector2i to a native Vector2i.
     * @return
     */
    public org.jsfml.system.Vector2i toVector2i() {
        return new org.jsfml.system.Vector2i(x, y);
    }

    /**
     * Adds a Vector2i to this.
     * Example: (a1, a2) + (b1, b2) = (a1 + b1, a2 + b2)
     * @param x the x coordinate of the Vector2i to add.
     * @param y the y coordinate of the Vector2i to add.
     * @return the Vector2i sum of the Vector2is.
     */
    public Vector2i add(int x, int y) {
        return new Vector2i(this.x + x, this.y + y);
    }

    /**
     * Adds a Vector2i to this.
     * Example: (a1, a2) + (b1, b2) = (a1 + b1, a2 + b2)
     * @param v the Vector2i to add.
     * @return the Vector2i sum of the Vector2is.
     */
    public Vector2i add(Vector2i v) {
        return add(v.x, v.y);
    }

    /**
     * Subtracts a Vector2i from this.
     * Example: (a1, a2) - (b1, b2) = (a1 - b1, a2 - b2)
     * @param x the x coordinate of the Vector2i to subtract.
     * @param y the y coordinate of the Vector2i to subtract.
     * @return the Vector2i difference of the Vector2is.
     */
    public Vector2i sub(int x, int y) {
        return add(-x, -y);
    }

    /**
     * Subtracts a Vector2i from this.
     * Example: (a1, a2) - (b1, b2) = (a1 - b1, a2 - b2)
     * @param v the Vector2i to subtract.
     * @return the Vector2i difference of the Vector2is.
     */
    public Vector2i sub(Vector2i v) {
        return sub(v.x, v.y);
    }

    /**
     * Returns the dot product of this and another Vector2i.
     * Example: (a1, a2) dot (b1, b2) = (a1 * b1, a2 * b2)
     * @param x the x coordinate of the Vector2i to dot. 
     * @param y the y coordinate of the Vector2i to dot.
     * @return the dot product of the Vector2is.
     */
    public int dot(int x, int y) {
        return this.x * x + this.y * y;
    }

    /**
     * Returns the dot product of this and another Vector2i.
     * Example: (a1, a2) dot (b1, b2) = (a1 * b1, a2 * b2)
     * @param v the Vector2i to dot this Vector2i with.
     * @return the dot product of the Vector2is.
     */
    public int dot(Vector2i v) {
        return dot(v.x, v.y);
    }

    /**
     * Scales a Vector2i based on a scalar.
     * Example: (a1, a2) scl (5) = (a1 * 5, a2 * 5)
     * @param scalar the value to scale this Vector2i by.
     * @return the scaled Vector2i.
     */
    public Vector2i scl(int scalar) {
        return new Vector2i(x * scalar, y * scalar);
    }

    /**
     * Divides (inversely scales) a Vector2i.
     * @param divisor the value to divide this Vector2i by.
     * @return the divided Vector2i. ZERO if divisor is 0.
     */
    public Vector2i div(int divisor) {
        if (divisor == 0) {
            return ZERO;
        }
        return scl(1 / divisor);
    }

    /**
     * Scales a Vector2i, and adds that scaled Vector2i to this Vector2i.
     * Example: (a1, a2) sclAdd ((b1, b2), 5) = (a1 + b1*5, a2 * b2*5)
     * @param v the Vector2i to scale.
     * @param scalar the value to scale v by.
     * @return the sum of this Vector2i and the scaled Vector2i.
     */
    public Vector2i sclAdd(Vector2i v, int scalar) {
        return add(v.scl(scalar));
    }

    /**
     * The length (magnitude) of this Vector2i.
     * @return the length of this Vector2i.
     */
    public int len() {
        return (int) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes this Vector2i.
     * @return this Vector2i's unit Vector2i.
     */
    public Vector2i normalize() {
        return div(len());
    }

    /**
     * Sets the length of this Vector2i.
     * @param len the length to be set.
     * @return this Vector2i with the specified length.
     */
    public Vector2i setLength(int len) {
        return normalize().scl(len);
    }

    /**
     * Clamps a Vector2i between two lengths.
     * @param min the minimum length of the Vector2i.
     * @param max the maximum length of this Vector2i.
     * @return a new Vector, such that min &lt= Vector.len() &lt= max.
     */
    public Vector2i clamp(int min, int max) {
        int len = len();
        if (len > max) {
            return setLength(max);
        } else if (len < min) {
            return setLength(min);
        }
        // length is already between bounds
        return this;
    }

    /**
     * Returns the distance between this and another Vector2i.
     * @param x the x value of the Vector2i.
     * @param y the y value of the Vector2i.
     * @return the distance between the two Vector2is.
     */
    public int dst(int x, int y) {
        // if two Vector2is are equal, distance is 0
        if (equals(x, y)) {
            return 0;
        }
        return sub(x, y).len();
    }

    /**
     * Returns the distance between this and another Vector2i.
     * @param v the Vector2i.
     * @return the distance between the two Vector2is.
     */
    public int dst(Vector2i v) {
        return dst(v.x, v.y);
    }

    /**
     * Returns the angle between this Vector2i and the x-axis.
     * @return the angle, in radians.
     */
    public int angle() {
        return (int) Math.atan2(y, x);
    }

    /**
     * Returns this angle between this Vector2i and another Vector2i.
     * @param v the Vector2i.
     * @return the smallest angle, in radians, between the two Vector2is.
     */
    public int angle(Vector2i v) {
        if (len() == 0 || v.len() == 0) {
            return 0;
        }
        return (int) Math.acos(dot(v) / (len() * v.len()));
    }
    
    /**
     * Returns if this Vector2i is equal to another Vector2i.
     * @param x the x value of the Vector2i
     * @param y the y value of the Vector2i
     * @return <code>true</code> if this.x == x AND this.y == y, <code>false</code> otherwise
     */
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    /**
     * Returns if this Vector2i is equal to another Vector2i.
     * @param v the Vector2i
     * @return <code>true</code> if this.x == x AND this.y == y, <code>false</code> otherwise
     */
    public boolean equals(Vector2i v) {
        return equals(v.x, v.y);
    }

    /**
     * Returns if this Vector2i is the zero vector.
     * @return <code>true</code> if this.equals(ZERO), <code>false</code> otherwise
     */
    public boolean isZero() {
        return equals(ZERO);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vector: <");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(">");
        return sb.toString();
    }

}

