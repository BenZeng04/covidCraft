
/**
 * @author Ben Zeng
 * Revision History:
 * Jun 16, 2020: Created ~Ben Zeng, 15m
 * A class representing the yellow door which the player walks out of to go to the next level.
 * @version 1
 */
public class SpecialDoor extends Interactable
{
    public SpecialDoor(int layer, int xStart, int yStart, int xEnd, int yEnd)
    {
        super(layer, xStart, yStart, xEnd, yEnd);
    }

    @Override
    public void whenInteractedWith()
    {
        Game game = getGame();
        Level level = game.getLevel(game.getCurrentLevel());
        Item finalItem = level.getObjective();
        boolean hasItem = false;
        for(Item i: game.getInventory())
        {
            if(finalItem.equals(i))
            {
                hasItem = true;
                break;
            }
        }
        if(hasItem)
        {
            if(game.getCurrentLevel() < 2)
            {
                DialogueGUI levelComplete = new DialogueGUI("CONGRATULATIONS! You have completed level " + game.getCurrentLevel() + "! It is time to move on to the next level! You will promptly be returned to your room.")
                {
                    @Override
                    public void whenExited()
                    {
                        game.incrementLevel();
                        addComponent(new TransitionEvent("Gameplay", "Alice's Room"));
                    }
                };
                addComponent(levelComplete);
            }
            else
            {
                DialogueGUI levelComplete = new DialogueGUI("CONGRATULATIONS! You have beat the game! You will promptly be returned to the main menu.")
                {
                    @Override
                    public void whenExited()
                    {
                        addComponent(new TransitionEvent("MainMenu"));
                    }
                };
                addComponent(levelComplete);
            }
        }
        else
        {
            DialogueGUI error = new DialogueGUI("What are you trying to do here? You haven't even crafted your item yet! Don't even think about it...")
            {
                @Override
                public void whenExited()
                {
                }
            };
            addComponent(error);
        }
    }
}
