package ending.vector;

/**
 * A class that manages 2D vectors of float values.
 *
 * @author Nick
 */
public class Vector2f {

    public final float x;

    public final float y;

    public static final Vector2f ZERO = new Vector2f(0, 0);
    
    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2f(org.jsfml.system.Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public org.jsfml.system.Vector2f toVector2f() {
        return new org.jsfml.system.Vector2f(x, y);
    }

    public Vector2f add(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    public Vector2f add(Vector2f v) {
        return add(v.x, v.y);
    }

    public Vector2f sub(float x, float y) {
        return add(-x, -y);
    }

    public Vector2f sub(Vector2f v) {
        return sub(v.x, v.y);
    }

    public float dot(float x, float y) {
        return this.x * x + this.y * y;
    }

    public float dot(Vector2f v) {
        return dot(v.x, v.y);
    }

    public Vector2f scl(float scalar) {
        return new Vector2f(x * scalar, y * scalar);
    }

    public Vector2f div(float divisor) {
        if (divisor == 0) {
            return ZERO;
        }
        return scl(1 / divisor);
    }

    public Vector2f sclAdd(Vector2f v, float scalar) {
        return add(v.scl(scalar));
    }

    public float len() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2f normalize() {
        return div(len());
    }

    public Vector2f setLength(float len) {
        return normalize().scl(len);
    }

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

    public float dst(float x, float y) {
        // if two vectors are equal, distance is 0
        if (Vector2f.this.epsilonEquals(x, y, 0.00000001f)) {
            return 0;
        }
        return sub(x, y).len();
    }

    public float dst(Vector2f v) {
        return dst(v.x, v.y);
    }

    public float angle() {
        return (float) Math.atan2(y, x);
    }

    public float angle(Vector2f v) {
        if (len() == 0 || v.len() == 0) {
            return 0;
        }
        return (float) Math.acos(dot(v) / (len() * v.len()));
    }
    
    public boolean epsilonEquals(float x, float y, float epsilon) {
        return Math.abs(this.x-x) < epsilon && Math.abs(this.y-y) < epsilon;
    }
    
    public boolean epsilonEquals(Vector2f v, float epsilon) {
        return epsilonEquals(v.x, v.y, epsilon);
    }

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
