package textures;

import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * Класс для текстурирования нашей модели
 */
public class ModelTexture {

    /** Идентификатор текстуры */
    private int textureId;
    
    /** коэффициент блеска материала */
    private float shineDamper = 1;
    /** отражательная способность(0 - нет отражения) */
    private float reflectivity = 0;
    /** имеет ли прозрачность текстура */
    private boolean hasTransparency = false;
    /** использование фальшивого освещения */
    private boolean useFakeLighting = false;

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public Vector4f getColor() {
        return color;
    }

    private Vector4f color = new Vector4f(0.0f, 0.0f, 0.0f,0);

    public ModelTexture(){}
    public ModelTexture(int textureId) {
        this.textureId = textureId;
    }

    public int getId() {
        return textureId;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
    public boolean isHasTransparency() {
        return hasTransparency;
    }
    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }
    public boolean isUseFakeLighting() {
        return useFakeLighting;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }
}

