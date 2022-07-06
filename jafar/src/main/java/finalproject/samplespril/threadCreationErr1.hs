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
    , WriteInstr regA (DirAddr 4) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 5) 
    , Nop 
    , ReadInstr (DirAddr 7) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 17) 
    , Nop 
    , ReadInstr (DirAddr 10) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 23) 
    , Nop 
    , ReadInstr (DirAddr 11) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 29) 
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
    , Nop 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 2) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 3) 
    , Nop 
    , ReadInstr (DirAddr 8) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 11) 
    , Nop 
    , ReadInstr (DirAddr 9) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 17) 
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
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 8) 
    , EndProg 
    ]

prog3 :: [Instruction]
prog3 = [
    Nop 
    , ReadInstr (DirAddr 3) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 9) 
    , EndProg 
    ]

prog4 :: [Instruction]
prog4 = [
    Nop 
    , ReadInstr (DirAddr 4) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 10) 
    , EndProg 
    ]

prog5 :: [Instruction]
prog5 = [
    Nop 
    , ReadInstr (DirAddr 5) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 11) 
    , EndProg 
    ]

main = run [prog0,prog1,prog2,prog3,prog4,prog5]

