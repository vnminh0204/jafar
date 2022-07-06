import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 1) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 2) 
    , Nop 
    , ReadInstr (DirAddr 7) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 15) 
    , Nop 
    , ReadInstr (DirAddr 8) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 21) 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

prog1 :: [Instruction]
prog1 = [
    Nop 
    , ReadInstr (DirAddr 1) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 20) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Jump (Abs 43) 
    , Nop 
    , Load (ImmValue 18) regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 20) regA 
    , Push regA 
    , ReadInstr (DirAddr 20) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Nop 
    , ReadInstr (DirAddr 20) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 15) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 7) 
    , EndProg 
    ]

prog2 :: [Instruction]
prog2 = [
    Nop 
    , ReadInstr (DirAddr 2) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 21) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Jump (Abs 43) 
    , Nop 
    , Load (ImmValue 18) regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 21) regA 
    , Push regA 
    , ReadInstr (DirAddr 21) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Nop 
    , ReadInstr (DirAddr 21) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 15) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 8) 
    , EndProg 
    ]

main = run [prog0,prog1,prog2]

