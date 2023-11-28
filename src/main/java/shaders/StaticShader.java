package shaders;

import entities.Camera;
import entities.Light;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import toolbox.Maths;

/**
 * Шейдер для создания всех статических моделей
 */
public class StaticShader extends ShaderProgram {

    // указываем пути в константы для загрузки шейдеров
    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.txt";
    
    // идентификатор юниформы матрицы трансформации
    private int location_transformationMatrix;
    // идентификатор юниформы матрицы проекции
    private int location_projectionMatrix;
    // идентификатор юниформы матрицы вида
    private int location_viewMatrix;
    
    private int location_lightPosition; // позиция источника света
    private int location_lightColour; // цвет источника света
    
    private int location_shineDamper; // коэффициент блеска материала
    private int location_reflectivity; // отражательная способность материала
    private int location_useFakeLighting; // фальшивое освещение
    private int location_textureColor; // цвет источника света
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        // связываем списки атрибутов VAO с шейдером 
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        // регистрируем юниформы
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColour = super.getUniformLocation("lightColour");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_useFakeLighting = super.getUniformLocation("useFakeLighting");
        location_textureColor = super.getUniformLocation("textureColor");
    }
    
    /**
     * Загрузка данных, матрицы трансформации, в юниформу
     * @param matrix матрица преобразования
     */
    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }
    
    /**
     * Загрузка позиции и цвета источника света, в юниформу
     * @param light источник света
     */
    public void loadLight(Light light) {
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColour, light.getColour());
    }
    
    public void loadTextureColor(Vector4f textureColor){
        loadVector(location_textureColor,textureColor);
    }
    
    /**
     * Загрузка переменных блеска/отражения
     * @param damper коэффициент блеска материала
     * @param reflectivity отражательная способность материала
     */
    public void loadShineVariables(float damper, float reflectivity) {
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }
    
    /**
     * Загрузка данных, матрицы вида, в юниформу
     * @param camera камера
     */
    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
    
    /**
     * Загрузка данных, матрицы проекции, в юниформу
     * @param matrix матрица проекции
     */
    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    /**
     * Загрузка логического значения фальшивого освещения
     * @param useFake логическое значение
     */
    public void loadFakeLightingVariable(boolean useFake) {
        super.loadBoolean(location_useFakeLighting, useFake);
    }


}

