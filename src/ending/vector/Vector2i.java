package ending.vector;

/**
 * A class that manages 2D vectors of int values.
 *
 * @author Nick
 */
public class Vector2i {

    public final int x;

    public final int y;

    public static final Vector2i ZERO = new Vector2i(0, 0);
    
    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2i(org.jsfml.system.Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    public org.jsfml.system.Vector2i toVector2i() {
        return new org.jsfml.system.Vector2i(x, y);
    }

    public Vector2i add(int x, int y) {
        return new Vector2i(this.x + x, this.y + y);
    }

    public Vector2i add(Vector2i v) {
        return add(v.x, v.y);
    }

    public Vector2i sub(int x, int y) {
        return add(-x, -y);
    }

    public Vector2i sub(Vector2i v) {
        return sub(v.x, v.y);
    }

    public int dot(int x, int y) {
        return this.x * x + this.y * y;
    }

    public int dot(Vector2i v) {
        return dot(v.x, v.y);
    }

    public Vector2i scl(int scalar) {
        return new Vector2i(x * scalar, y * scalar);
    }

    public Vector2i div(int divisor) {
        if (divisor == 0) {
            return ZERO;
        }
        return scl(1 / divisor);
    }

    public Vector2i sclAdd(Vector2i v, int scalar) {
        return add(v.scl(scalar));
    }

    public int len() {
        return (int) Math.sqrt(x * x + y * y);
    }

    public Vector2i normalize() {
        return div(len());
    }

    public Vector2i setLength(int len) {
        return normalize().scl(len);
    }

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

    public int dst(int x, int y) {
        // if two vectors are equal, distance is 0
        if (equals(x, y)) {
            return 0;
        }
        return sub(x, y).len();
    }

    public int dst(Vector2i v) {
        return dst(v.x, v.y);
    }

    public int angle() {
        return (int) Math.atan2(y, x);
    }

    public int angle(Vector2i v) {
        if (len() == 0 || v.len() == 0) {
            return 0;
        }
        return (int) Math.acos(dot(v) / (len() * v.len()));
    }
    
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    public boolean equals(Vector2i v) {
        return Vector2i.this.equals(v.x, v.y);
    }

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

