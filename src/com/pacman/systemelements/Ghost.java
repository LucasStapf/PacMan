package com.pacman.systemelements;

import com.pacman.engine.DijkstraAlgorithm;
import com.pacman.engine.GameManager;
import com.pacman.engine.Vertex;
import java.util.LinkedList;

/**
 * Classe que representa os fantasmas do jogo.
 */
public final class Ghost extends DynamicGameObject {

    /**
     * Constantes que descrevem o tipo de movimento do Ghost.
     */
    public enum Movement {
        FOLLOW_TARGET,
        RUN_AWAY_TARGET,
        RANDOM;
    }

    /**
     * Atributo que guarda o tipo de movimento do Ghost.
     */
    private Movement movement = Movement.RANDOM;

    /**
     * Atributo que guarda o alvo que será perseguido pelo Ghost.
     */
    private GameObject target;

    /**
     * Atributo que guarda o caminho a ser seguido até o alvo.
     */
    private LinkedList<Vertex<Floor>> pathToTarget = new LinkedList<>();

    /**
     * Construtor padrão.
     * Por padrão, a camada do Ghost é 4 (layer = 4) e é um corpo rígido (rigidBody = true).
     * @param position posição onde será criado o Ghost.
     */
    public Ghost(Position position) {
        setLayer(4);
        setRigidBody(true);
        setPosition(position);
        getVelocity().setModulus(1);
    }

    /**
     * Retorna o atual tipo de movimento do Ghost.
     * @return movement
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Retorna o alvo do Ghost.
     * @return target
     */
    public GameObject getTarget() {
        return target;
    }

    /**
     * Define o tipo de movimento do Ghost.
     * @param movement novo movimento.
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    /**
     * Altera o alvo do Ghost.
     * @param target novo alvo do Ghost.
     */
    public void setTarget(GameObject target) {
        this.target = target;
    }

    /**
     * Atualiza o caminho até o alvo.
     */
    private void updatePathToTarget() {

        if (target == null) {
            pathToTarget.clear();
            return;
        }

        if (pathToTarget != null && !pathToTarget.isEmpty()) {
            if (target.isOnFloor(pathToTarget.getLast().getT())) return;
            else for (Vertex<Floor> vertex: pathToTarget) vertex.getT().highlighted = false; // temp
        }

        Floor floor, targetFloor;

        floor = GameManager.getArenaManager().getArena().getGameObjectFloorHashMap().get(this);
        targetFloor = GameManager.getArenaManager().getArena().getGameObjectFloorHashMap().get(target);

        DijkstraAlgorithm dijAlg = new DijkstraAlgorithm(GameManager.getArenaManager().getGraph(), floor.getVertex());
        pathToTarget = dijAlg.getShortestPath(targetFloor.getVertex());

        for (Vertex<Floor> vertex: pathToTarget) vertex.getT().highlighted = true; // temp

    }

    /**
     * Faz o Ghost percorrer o caminho até o alvo.
     */
    private void followPath() {

        if (pathToTarget.isEmpty()) return;

        if (isCenteredOnFloor(pathToTarget.getFirst().getT())) {

            pathToTarget.getFirst().getT().highlighted = false;
            pathToTarget.removeFirst();
            if (pathToTarget.isEmpty()) return;

            float deltaX = pathToTarget.getFirst().getT().getPosition().getX() - getPosition().getX();
            float deltaY = pathToTarget.getFirst().getT().getPosition().getY() - getPosition().getY();

            if (deltaX > 0) getVelocity().setDirection(Velocity.Direction.RIGHT);
            else if (deltaX < 0) getVelocity().setDirection(Velocity.Direction.LEFT);
            else if (deltaY > 0) getVelocity().setDirection(Velocity.Direction.UP);
            else if (deltaY < 0) getVelocity().setDirection(Velocity.Direction.DOWN);
        }

        translate(1);
    }

    @Override
    public void update() {

        switch (movement) {

            case RANDOM:
                break;

            case FOLLOW_TARGET:
                updatePathToTarget();
                followPath();
                break;

            case RUN_AWAY_TARGET:
                break;
        }
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {
        System.out.print("G");
    }
}
