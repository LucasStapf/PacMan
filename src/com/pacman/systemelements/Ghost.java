package com.pacman.systemelements;

import com.pacman.engine.DijkstraAlgorithm;
import com.pacman.engine.SystemGame;
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
     * @param position {@link Position} onde será criado o Ghost.
     */
    public Ghost(Position position) {
        setLayer(4);
        setRigidBody(false);
        setPosition(position);
        setOldPosition(position);
        setDimension(new Dimension(18, 18));
        getVelocity().setModulus(50);
    }

    /**
     * Retorna o atual tipo de movimento do Ghost.
     * @return o atual tipo de movimento do Ghost.
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Retorna o alvo do Ghost.
     * @return o alvo do Ghost.
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

        floor = Floor.getFloorFrom(this);
        targetFloor = Floor.getFloorFrom(target);

        DijkstraAlgorithm dijAlg = new DijkstraAlgorithm(SystemGame.getArenaManager().getGraph(), floor.getVertex());
        pathToTarget = dijAlg.getShortestPath(targetFloor.getVertex());

        for (Vertex<Floor> vertex: pathToTarget) vertex.getT().highlighted = true; // temp
    }

    /**
     * Faz o Ghost percorrer o caminho até o alvo.
     */
    private void followPath() {

        if (pathToTarget.isEmpty()) return;

        if (pathToTarget.size() >= 2) {

            double dX, dY;
            dX = pathToTarget.get(1).getT().getPosition().getX() - pathToTarget.get(0).getT().getPosition().getX();
            dY = pathToTarget.get(1).getT().getPosition().getY() - pathToTarget.get(0).getT().getPosition().getY();

            if (dX > 0) changeDirectionTo(Velocity.Direction.RIGHT);
            else if (dX < 0) changeDirectionTo(Velocity.Direction.LEFT);
            else if (dY > 0) changeDirectionTo(Velocity.Direction.UP);
            else if (dY < 0) changeDirectionTo(Velocity.Direction.DOWN);

            if (isOnFloor(pathToTarget.get(1).getT())) {
                pathToTarget.removeFirst();
            }
        }

//        if (isOnFloor(pathToTarget.getFirst().getT())) {
//
//            pathToTarget.getFirst().getT().highlighted = false;
//            pathToTarget.removeFirst();
//            if (pathToTarget.isEmpty()) return;
//
//            double deltaX = pathToTarget.getFirst().getT().getPosition().getX() - getPosition().getX();
//            double deltaY = pathToTarget.getFirst().getT().getPosition().getY() - getPosition().getY();
//
//            if (deltaX > 0) changeDirectionTo(Velocity.Direction.RIGHT);
//            else if (deltaX < 0) changeDirectionTo(Velocity.Direction.LEFT);
//            else if (deltaY > 0) changeDirectionTo(Velocity.Direction.UP);
//            else if (deltaY < 0) changeDirectionTo(Velocity.Direction.DOWN);
//
//        }

        translate(SystemGame.deltaTime);
    }

    @Override
    public void update() {

        switch (movement) {

            case RANDOM:
                translate(SystemGame.deltaTime);
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
        java.lang.System.out.print("G");
    }
}
