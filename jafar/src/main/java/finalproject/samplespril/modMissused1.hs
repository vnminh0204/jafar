import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
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
    , Branch regA (Abs 8) 
    , Nop 
    , ReadInstr (DirAddr 8) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 19) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 64) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 50) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 46) 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , ReadInstr (DirAddr 19) 
    , Receive regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (DirAddr 19) 
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
    , TestAndSet (DirAddr 12) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 6) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 19) regA 
    , Push regA 
    , ReadInstr (DirAddr 17) 
    , Receive regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 59) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 42) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 38) 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , ReadInstr (DirAddr 19) 
    , Receive regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (DirAddr 19) 
    , Receive regA 
    , WriteInstr regA numberIO 
    , WriteInstr reg0 (DirAddr 12) 
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
    , TestAndSet (DirAddr 12) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 6) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 19) regA 
    , Push regA 
    , ReadInstr (DirAddr 17) 
    , Receive regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regB reg0 regD 
    , Branch regD (Abs 59) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 42) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 38) 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , ReadInstr (DirAddr 19) 
    , Receive regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (DirAddr 19) 
    , Receive regA 
    , WriteInstr regA numberIO 
    , WriteInstr reg0 (DirAddr 12) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 8) 
    , EndProg 
    ]

main = run [prog0,prog1,prog2]

