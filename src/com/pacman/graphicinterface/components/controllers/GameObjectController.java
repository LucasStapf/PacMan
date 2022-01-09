package com.pacman.graphicinterface.components.controllers;

import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import javafx.animation.KeyFrame;

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
     * @throws IllegalArgumentException caso a classe do {@link SceneElementGraphic} não seja a requerida.
     */
    void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException;

    /**
     * Retorna o KeyFrame responsável pela translação do {@link GameObject} em questão.
     * @return o KeyFrame responsável pela translação do {@link GameObject} em questão.
     */
    KeyFrame getTranslationKeyFrame();

    /**
     * Retorna o KeyFrame responsável pela animação do {@link GameObject} em questão.
     * @return o KeyFrame responsável pela animação do {@link GameObject} em questão.
     */
    KeyFrame getAnimationKeyFrame();

    /**
     * Método chamado a nova Timeline iniciada.
     */
    void update();

    /**
     * Destrói tanto o {@link GameObject} controlado quanto sua representação gráfica.
     */
    void destroy();


}
