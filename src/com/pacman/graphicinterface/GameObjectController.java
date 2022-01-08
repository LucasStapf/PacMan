package com.pacman.graphicinterface;

import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import javafx.animation.KeyFrame;
import javafx.scene.layout.Region;

public interface GameObjectController {

    /**
     * Retorna o {@link GameObject} controlado.
     * @return o {@link GameObject} controlado.
     */
    GameObject getGameObject();

    /**
     * Altera o {@link GameObject} controlado.
     * @param gameObject novo {@link GameObject}.
     * @throws IllegalArgumentException caso a classe do objeto não seja a requerida.
     */
    void setGameObject(GameObject gameObject) throws IllegalArgumentException;

    /**
     * Retorna a representação gráfica do {@link GameObject} controlado.
     * @return a representação gráfica do {@link GameObject} controlado.
     */
    SceneElementGraphic getSceneElementGraphic();

    /**
     * Altera a representação gráfica do {@link GameObject} controlado.
     * @param sceneElementGraphic nova representação gráfica.
     */
    void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic);

    /**
     * Retorna o KeyFrame responsável pela translação do {@link GameObject} em questão.
     * @return o KeyFrame responsável pela translação do {@link GameObject} em questão.
     * @param x abscissa do ponto de chegada.
     * @param y ordenada do ponto de chegada.
     */
    KeyFrame getTranslationKeyFrame(double x, double y);

    /**
     * Retorna o KeyFrame responsável pela animação do {@link GameObject} e questão.
     * @return o KeyFrame responsável pela animação do {@link GameObject} e questão.
     */
    KeyFrame getAnimationKeyFrame();

    /**
     * Destrói tanto o {@link GameObject} controlado quanto sua representação gráfica.
     */
    void destroy();

}
