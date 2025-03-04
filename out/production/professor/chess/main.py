import chess
import chess.engine

# Evaluate the board position
def evaluate_board(board):
    """
    Simple evaluation function based on piece values.
    Positive = advantage for white, Negative = advantage for black.
    """
    piece_values = {'P': 1, 'N': 3, 'B': 3, 'R': 5, 'Q': 9, 'K': 1000}
    score = 0
    for square in chess.SQUARES:
        piece = board.piece_at(square)
        if piece:
            value = piece_values.get(piece.symbol().upper(), 0)
            if piece.color == chess.WHITE:
                score += value
            else:
                score -= value
    return score

# Minimax with alpha-beta pruning
def minimax(board, depth, alpha, beta, is_maximizing):
    if depth == 0 or board.is_game_over():
        return evaluate_board(board)

    if is_maximizing:
        max_eval = float('-inf')
        for move in board.legal_moves:
            board.push(move)
            eval = minimax(board, depth - 1, alpha, beta, False)
            board.pop()
            max_eval = max(max_eval, eval)
            alpha = max(alpha, eval)
            if beta <= alpha:
                break
        return max_eval
    else:
        min_eval = float('inf')
        for move in board.legal_moves:
            board.push(move)
            eval = minimax(board, depth - 1, alpha, beta, True)
            board.pop()
            min_eval = min(min_eval, eval)
            beta = min(beta, eval)
            if beta <= alpha:
                break
        return min_eval

# Find the best move
def find_best_move(board, depth):
    best_move = None
    best_value = float('-inf')
    alpha = float('-inf')
    beta = float('inf')

    for move in board.legal_moves:
        board.push(move)
        board_value = minimax(board, depth - 1, alpha, beta, False)
        board.pop()
        if board_value > best_value:
            best_value = board_value
            best_move = move

    return best_move

# Main script
def main():
    board = chess.Board()
    print("Initial board:")
    print(board)

    depth = 3  # Search depth

    while not board.is_game_over():
        # Player's turn (white)
        print("\nYour turn (White):")
        print(board)
        move = input("Enter your move in UCI format (e.g., e2e4): ")
        try:
            board.push_uci(move)
        except ValueError:
            print("Invalid move. Try again.")
            continue

        if board.is_game_over():
            break

        # AI's turn (black)
        print("\nAI's turn (Black)...")
        best_move = find_best_move(board, depth)
        board.push(best_move)
        print(f"AI played: {best_move}")
        print(board)

    # Game over
    print("\nGame over!")
    print(board.result())

if __name__ == "__main__":
    main()
