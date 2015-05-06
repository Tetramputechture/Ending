package ending.vector;

/**
 * A class that manages 2D vectors of float values.
 *
 * @author Nick
 */
public class Vector2f {

    /**
     * The x value of this Vector2f.
     */
    public final float x;

    /**
     * The y value of this Vector2f.
     */
    public final float y;

    /**
     * The Zero Vector2f, defined as (0, 0).
     */
    public static final Vector2f ZERO = new Vector2f(0, 0);
    
    /**
     * Constructs a new Vector2f, with both x and y set to 0.
     */
    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a new Vector2f based off x and y parameters.
     * @param x the x value to set this Vector2f to.
     * @param y the y value to set this Vector2f to.
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector2f based off of another Vector2f.
     * @param v the Vector2f to have this Vector2f constructed from.
     */
    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Constructs a new Vector2f based off of JSFML's Vector2f class.
     * @param v
     */
    public Vector2f(org.jsfml.system.Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    /**
     * Constructs a new Vector2f based off of JSFML's Vector2i class.
     * @param v
     */
    public Vector2f(org.jsfml.system.Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Converts a JSFML Vector2f to a native Vector2f.
     * @return
     */
    public org.jsfml.system.Vector2f toVector2f() {
        return new org.jsfml.system.Vector2f(x, y);
    }

    /**
     * Adds a Vector2f to this.
     * Example: (a1, a2) + (b1, b2) = (a1 + b1, a2 + b2)
     * @param x the x coordinate of the Vector2f to add.
     * @param y the y coordinate of the Vector2f to add.
     * @return the Vector2f sum of the Vector2f's.
     */
    public Vector2f add(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    /**
     * Adds a Vector2f to this.
     * Example: (a1, a2) + (b1, b2) = (a1 + b1, a2 + b2)
     * @param v the Vector2f to add.
     * @return the Vector2f sum of the Vector2f's.
     */
    public Vector2f add(Vector2f v) {
        return add(v.x, v.y);
    }

    /**
     * Subtracts a Vector2f from this.
     * Example: (a1, a2) - (b1, b2) = (a1 - b1, a2 - b2)
     * @param x the x coordinate of the Vector2f to subtract.
     * @param y the y coordinate of the Vector2f to subtract.
     * @return the Vector2f difference of the Vector2f's.
     */
    public Vector2f sub(float x, float y) {
        return add(-x, -y);
    }

    /**
     * Subtracts a Vector2f from this.
     * Example: (a1, a2) - (b1, b2) = (a1 - b1, a2 - b2)
     * @param v the Vector2f to subtract.
     * @return the Vector2f difference of the Vector2f's.
     */
    public Vector2f sub(Vector2f v) {
        return sub(v.x, v.y);
    }

    /**
     * Returns the dot product of this and another Vector2i.
     * Example: (a1, a2) dot (b1, b2) = (a1 * b1, a2 * b2)
     * @param x the x coordinate of the Vector2f to dot. 
     * @param y the y coordinate of the Vector2f to dot.
     * @return the dot product of the Vector2f's.
     */
    public float dot(float x, float y) {
        return this.x * x + this.y * y;
    }

    /**
     * Returns the dot product of this and another Vector2i.
     * Example: (a1, a2) dot (b1, b2) = (a1 * b1, a2 * b2)
     * @param v the Vector2f to dot this Vector2f with.
     * @return the dot product of the Vector2f's.
     */
    public float dot(Vector2f v) {
        return dot(v.x, v.y);
    }

    /**
     * Scales a Vector2f based on a scalar.
     * Example: (a1, a2) scl (5) = (a1 * 5, a2 * 5)
     * @param scalar the value to scale this Vector2f by.
     * @return the scaled Vector2f.
     */
    public Vector2f scl(float scalar) {
        return new Vector2f(x * scalar, y * scalar);
    }

    /**
     * Divides (inversely scales) a Vector2f.
     * @param divisor the value to divide this Vector2f by.
     * @return the divided Vector2f. ZERO if divisor is 0.
     */
    public Vector2f div(float divisor) {
        if (divisor == 0) {
            return ZERO;
        }
        return scl(1 / divisor);
    }

    /**
     * Scales a Vector2f, and adds that scaled Vector2f to this Vector2f.
     * Example: (a1, a2) sclAdd ((b1, b2), 5) = (a1 + b1*5, a2 * b2*5)
     * @param v the Vector2f to scale.
     * @param scalar the value to scale v by.
     * @return the sum of this Vector2f and the scaled Vector2f.
     */
    public Vector2f sclAdd(Vector2f v, float scalar) {
        return add(v.scl(scalar));
    }

    /**
     * The length (magnitude) of this Vector2f.
     * @return the length of this Vector2f.
     */
    public float len() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes this Vector2f.
     * @return this Vector2f's unit Vector2f.
     */
    public Vector2f normalize() {
        return div(len());
    }

    /**
     * Sets the length of this Vector2f.
     * @param len the length to be set.
     * @return this Vector2f with the specified length.
     */
    public Vector2f setLength(float len) {
        return normalize().scl(len);
    }

    /**
     * Clamps a Vector2f between two lengths.
     * @param min the minimum length of the Vector2f.
     * @param max the maximum length of this Vector2f.
     * @return a new Vector, such that min &lt= Vector.len() &lt= max.
     */
    public Vector2f clamp(float min, float max) {
        float len = len();
        if (len > max) {
            return setLength(max);
        } else if (len < min) {
            return setLength(min);
        }
        // length is already between bounds
        return this;
    }

    /**
     * Returns the distance between this and another Vector2f.
     * @param x the x value of the Vector2f.
     * @param y the y value of the Vector2f.
     * @return the distance between the two Vector2f's.
     */
    public float dst(float x, float y) {
        // if two Vector2f's are equal, distance is 0
        if (Vector2f.this.epsilonEquals(x, y, 0.00000001f)) {
            return 0;
        }
        return sub(x, y).len();
    }

    /**
     * Returns the distance between this and another Vector2f.
     * @param v the Vector2f.
     * @return the distance between the two Vector2f's.
     */
    public float dst(Vector2f v) {
        return dst(v.x, v.y);
    }

    /**
     * Returns the angle between this Vector2f and the x-axis.
     * @return the angle, in radians.
     */
    public float angle() {
        return (float) Math.atan2(y, x);
    }

    /**
     * Returns this angle between this Vector2f and another Vector2f.
     * @param v the Vector2f.
     * @return the smallest angle, in radians, between the two Vector2f's.
     */
    public float angle(Vector2f v) {
        if (len() == 0 || v.len() == 0) {
            return 0;
        }
        return (float) Math.acos(dot(v) / (len() * v.len()));
    }
    
    /**
     * Returns if this Vector2i is equal to another Vector2i based on an error bound.
     * @param x the x value of the Vector2f.
     * @param y the y value of the Vector2f.
     * @param epsilon the error bound of the comparison.
     * @return <code>true</code> if abs(this.x-x) &lt epsilon AND abs(this.y-y) &lt epsilon,
     * <code>false</code> otherwise.
     */
    public boolean epsilonEquals(float x, float y, float epsilon) {
        return Math.abs(this.x-x) < epsilon && Math.abs(this.y-y) < epsilon;
    }
    
    /**
     * Returns if this Vector2f is equal to another Vector2f based on an error bound.
     * @param v the Vector2f.
     * @param epsilon the error bound of the comparison.
     * @return <code>true</code> if abs(this.x-v.x) &lt epsilon AND abs(this.y-v.y) &lt epsilon,
     * <code>false</code> otherwise.
     */
    public boolean epsilonEquals(Vector2f v, float epsilon) {
        return epsilonEquals(v.x, v.y, epsilon);
    }

    /**
     * Returns if this Vector2f is the zero Vector2f.
     * @return <code>true</code> if this.epsilonEquals(ZERO, 0.00000001f), 
     * <code>false</code> otherwise.
     */
    public boolean isZero() {
        return epsilonEquals(ZERO, 0.00000001f);
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
