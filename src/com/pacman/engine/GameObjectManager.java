package com.pacman.engine;

import com.pacman.graphicinterface.components.controllers.*;
import com.pacman.graphicinterface.components.javafx.*;
import com.pacman.systemelements.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe responsável por gerenciar todos os GameObject do jogo.
 */
public class GameObjectManager {

    /**
     * Guarda a posição central da Arena para onde os Ghosts são teleportados quando comidos pelo PacMan.
     */
    private PlaceGraphic begin;

    /**
     * Retorna a posição central da Arena.
     * @return a posição central da Arena.
     */
    public PlaceGraphic begin() {
        return begin;
    }

    /**
     * Altera a posição central da Arena.
     * @param begin nova posição.
     */
    public void setBegin(PlaceGraphic begin) {
        this.begin = begin;
    }

    /**
     * Atributo que representa o controlador do Pac-Man do jogador.
     */
    private PacManController player;

    /**
     * Retorna o controlador do PacMan.
     * @return o controlador do PacMan.
     */
    public PacManController player() {
        return player;
    }

    /**
     * Altera o controlador do PacMan.
     * @param player novo controlador.
     */
    public void setPlayer(PacManController player) {
        this.player = player;
    }

    /**
     * Ghost Blinky. Seu movimento é de perseguir o PacMan
     * aumentanto sua velocidade conforme o PacMan come PacDots.
     */
    private GhostController blinky;

    /**
     * Cor original do Ghost Blicky.
     */
    public final Color colorBlinky = Color.RED;

    /**
     * Retorna o Ghost Blinky.
     * @return o Ghost Blinky.
     */
    public GhostController blinky() {
        return blinky;
    }

    /**
     * Altera o Ghost Blinky.
     * @param blinky novo Ghost Blinky.
     */
    public void setBlinky(GhostController blinky) {
        this.blinky = blinky;
    }

    /**
     * Ghost Pinky. Seu movimento é de perseguir o PacMan com velocidade constante.
     */
    private GhostController pinky;

    /**
     * Cor original do Ghost Pinky.
     */
    public final Color colorPinky = Color.PINK;

    /**
     * Retorna o Ghost Pinky.
     * @return o Ghost Pinky.
     */
    public GhostController pinky() {
        return pinky;
    }

    /**
     * Altera o Ghost Pinky.
     * @param pinky novo Ghost Pinky.
     */
    public void setPinky(GhostController pinky) {
        this.pinky = pinky;
    }

    /**
     * Ghost Inky. Seu movimento é aleatório.
     */
    private GhostController inky;

    /**
     * Cor original do Ghost Inky.
     */
    public final Color colorInky = Color.LIMEGREEN;

    /**
     * Retorna o Ghost Inky.
     * @return o Ghost Inky.
     */
    public GhostController inky() {
        return inky;
    }

    /**
     * Altera o Ghost Inky.
     * @param inky novo Ghost Inky.
     */
    public void setInky(GhostController inky) {
        this.inky = inky;
    }

    /**
     * Ghost Clyde. Seu movimento é aleatório.
     */
    private GhostController clyde;

    /**
     * Cor original do Ghost Clyde.
     */
    public final Color colorClyde = Color.ORANGE;

    /**
     * Retorna o Ghost Clyde.
     * @return o Ghost Clyde.
     */
    public GhostController clyde() {
        return clyde;
    }

    /**
     * Altera o Ghost Clyde.
     * @param clyde novo Ghost Clyde.
     */
    public void setClyde(GhostController clyde) {
        this.clyde = clyde;
    }

    /**
     * Módulo da velocidade padrão de todos os objetos dinâmicos do jogo.
     */
    public static final float defaultModulus = 100.0f;

    /**
     * Módulo da velocidade durante o efeito da pílula de energia.
     */
    public static final float modulusInEffect = 80.0f;

    /**
     * Lista de controladores de todos os {@link SceneElement} presentes na arena do jogo.
     */
    private LinkedList<GameObjectController> gameObjectControllers;

    /**
     * Retorna a lista de todos os controladores do jogo.
     * @return a lista de todos os controladores do jogo.
     */
    public LinkedList<GameObjectController> gameObjectControllers() {
        return gameObjectControllers;
    }

    /**
     * Altera a lista de controladores do jogo.
     * @param gameObjectControllers nova lista.
     */
    public void setGameObjectControllers(LinkedList<GameObjectController> gameObjectControllers) {
        this.gameObjectControllers = gameObjectControllers;
    }

    /**
     * Atualiza a lista de {@link GameObjectController} a partir dos elementos presentes na Arena do jogo.
     */
    public void updateGameObjectControllers() {

        gameObjectControllers.clear();
        dynamicControllers.clear();
        staticControllers.clear();
        objectsToDestroy.clear();

        Iterator<Node> nodeIterator = GameSystem.screen.arena().getChildren().listIterator();

        while (nodeIterator.hasNext()) {

            GameObjectController gameObjectController;
            Node node = nodeIterator.next();
            SceneElementGraphic sceneElementGraphic = (SceneElementGraphic) node;

            double x = ScreenManager.convertScreenToGameX(node);
            double y = ScreenManager.convertScreenToGameY(node);

            Position position = new Position(x, y);
            Dimension dimension = new Dimension(sceneElementGraphic.getWidthValue(), sceneElementGraphic.getHeightValue());


            if (sceneElementGraphic instanceof WallGraphic) {

                gameObjectController = new WallController();

                Wall wall = new Wall(position, Wall.Orientation.HORIZONTAL);
                wall.setDimension(dimension);

                gameObjectController.setGameObject(wall);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                staticControllers.add(gameObjectController);

            } else if (sceneElementGraphic instanceof PacManGraphic) {

                gameObjectController = new PacManController();

                PacMan pacMan = new PacMan(position);
                pacMan.setDefaultPosition(position);
                pacMan.setDimension(dimension);
                pacMan.getVelocity().updateVelocity(defaultModulus, Direction.RIGHT);

                player = (PacManController) gameObjectController;
                ((PacManGraphic) sceneElementGraphic).requestFocus();

                gameObjectController.setGameObject(pacMan);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                dynamicControllers.add(gameObjectController);

            } else if (sceneElementGraphic instanceof PacDotGraphic) {

                gameObjectController = new PacDotController();

                PacDot pacDot = new PacDot(position);
                pacDot.setDimension(dimension);

                gameObjectController.setGameObject(pacDot);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                staticControllers.add(gameObjectController);

                GameSystem.levelManager.addPacDot();

            } else if (sceneElementGraphic instanceof FloorGraphic) {

                FloorGraphic floorGraphic = (FloorGraphic) sceneElementGraphic;

                int j = (int) Math.round(floorGraphic.getLayoutX() / floorGraphic.getWidthValue());
                int i = (int) Math.round(floorGraphic.getLayoutY() / floorGraphic.getHeightValue());

                Floor floor = new Floor(position);
                floor.setDimension(dimension);

                GameSystem.arenaManager.getArena().getBoard().get(i).set(j, floor);
                GameSystem.arenaManager.getGraph().addVertex(floor.getVertex());

            } else if (sceneElementGraphic instanceof GhostGraphic) {

                gameObjectController = new GhostController();

                Ghost ghost = new Ghost(position);
                ghost.setDefaultPosition(position);
                ghost.setDimension(dimension);
                ghost.getVelocity().updateVelocity(defaultModulus, Direction.DOWN);

                GhostGraphic ghostGraphic = (GhostGraphic) sceneElementGraphic;

                if (ghostGraphic.getBodyColor() == Color.PINK) pinky = (GhostController) gameObjectController;
                else if (ghostGraphic.getBodyColor() == Color.RED) blinky = (GhostController) gameObjectController;
                else if (ghostGraphic.getBodyColor() == Color.LIMEGREEN) inky = (GhostController) gameObjectController;
                else clyde = (GhostController) gameObjectController;

                gameObjectController.setGameObject(ghost);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                dynamicControllers.add(gameObjectController);

            } else if (sceneElementGraphic instanceof EnergyPillGraphic) {

                gameObjectController = new EnergyPillController();

                EnergyPill energyPill = new EnergyPill(position);
                energyPill.setDimension(dimension);

                gameObjectController.setGameObject(energyPill);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                staticControllers.add(gameObjectController);

                GameSystem.levelManager.addEnergyPill();

            } else if (sceneElementGraphic instanceof PlaceGraphic) {

                gameObjectController = new PlaceController();

                Place place = new Place(position);
                place.setDimension(dimension);

                gameObjectController.setGameObject(place);
                gameObjectController.setSceneElementGraphic(sceneElementGraphic);

                gameObjectControllers.add(gameObjectController);
                staticControllers.add(gameObjectController);

                begin = (PlaceGraphic) sceneElementGraphic;
            }
        }

        setDefaultPropertiesGhosts();

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 27; j++) {
                GameSystem.arenaManager.updateEdgesArena(GameSystem.arenaManager.getArena().getBoard(), i, j);
            }
        }
    }

    public void setDefaultPropertiesGhosts() {

        blinky.getGhost().setTarget(player.getPacMan());
        blinky.getGhost().setMovement(Ghost.Movement.FOLLOW_TARGET);

        pinky.getGhost().setTarget(player.getPacMan());
        pinky.getGhost().setMovement(Ghost.Movement.FOLLOW_TARGET);

        inky.getGhost().setMovement(Ghost.Movement.RANDOM);
        clyde.getGhost().setMovement(Ghost.Movement.RANDOM);
    }

    /**
     * Lista de todos os controladores que controlam {@link GameObject} que podem se mover.
     */
    private LinkedList<GameObjectController> dynamicControllers;

    /**
     * Retorna a lista de controladores de objetos que podem se mover.
     * @return a lista de controladores de objetos que podem se mover.
     */
    public LinkedList<GameObjectController> dynamicControllers() {
        return dynamicControllers;
    }

    /**
     * Altera a lista de controladores dos objetos que podem se mover.
     * @param dynamicControllers nova lista.
     */
    public void setDynamicControllers(LinkedList<GameObjectController> dynamicControllers) {
        this.dynamicControllers = dynamicControllers;
    }

    /**
     * Lista de todos os controladores que controlam {@link GameObject} que não se movem.
     */
    private LinkedList<GameObjectController> staticControllers;

    /**
     * Retorna a lista de todos os controladores de objetos que não se movem.
     * @return a lista de todos os controladores de objetos que não se movem.
     */
    public LinkedList<GameObjectController> staticControllers() {
        return staticControllers;
    }

    /**
     * Altera a lista de controladores de objetos que não se movem.
     * @param staticControllers nova lista.
     */
    public void setStaticControllers(LinkedList<GameObjectController> staticControllers) {
        this.staticControllers = staticControllers;
    }

    /**
     * Lista de {@link GameObject} a serem destruidos.
     */
    private LinkedList<GameObject> objectsToDestroy;

    public LinkedList<GameObject> getObjectsToDestroy() {
        return objectsToDestroy;
    }

    public void destroyGameObjects(){

        Iterator<GameObject> gameObjectIterator = objectsToDestroy.iterator();

        while (gameObjectIterator.hasNext()) {

            GameObject gameObject = gameObjectIterator.next();
            Iterator<GameObjectController> gameObjectControllerIterator = gameObjectControllers.iterator();

            while (gameObjectControllerIterator.hasNext()) {

                GameObjectController gameObjectController = gameObjectControllerIterator.next();

                if (gameObjectController.getGameObject().equals(gameObject)) {
                    gameObjectControllerIterator.remove();
                    gameObjectIterator.remove();
                    dynamicControllers.remove(gameObjectController);
                    staticControllers.remove(gameObjectController);
                    GameSystem.screen.arena().getChildren().remove(gameObjectController.getSceneElementGraphic());
                }
            }
        }
    }

    /**
     * Construtor padrão.
     */
    public GameObjectManager() {

        gameObjectControllers = new LinkedList<>();
        dynamicControllers = new LinkedList<>();
        staticControllers = new LinkedList<>();
        objectsToDestroy = new LinkedList<>();
    }

}
