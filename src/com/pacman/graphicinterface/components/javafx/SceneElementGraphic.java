package com.pacman.graphicinterface.components.javafx;

public interface SceneElementGraphic {


    /**
     * Retorna o comprimento do SceneElement.
     * @return o comprimento do SceneElement.
     */
    double getWidthValue();

    /**
     * Altera o comprimento do SceneElement.
     * @param widthValue novo comprimento.
     */
    void setWidthValue(double widthValue);

    /**
     * Retorna a altura do SceneElement.
     * @return a altura do SceneElement.
     */
    double getHeightValue();

    /**
     * Altera a altura do SceneElement.
     * @param heightValue nova altura.
     */
    void setHeightValue(double heightValue);

}
