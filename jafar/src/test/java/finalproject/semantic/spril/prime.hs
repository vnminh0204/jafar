import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 3) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 77) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 74) 
    , Nop 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regB reg0 regD 
    , Branch regD (Abs 125) 
    , Load (ImmValue 0) regC 
    , Jump (Abs 44) 
    , Nop 
    , Compute Sub regA regB regA 
    , Load (ImmValue 1) regD 
    , Compute Add regC regD regC 
    , Nop 
    , Compute GtE regA regB regD 
    , Branch regD (Abs 40) 
    , Push regC 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 3) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regB regA regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute And regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 25) 
    , Load (DirAddr 3) regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 119) 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Jump (Abs 124) 
    , Nop 
    , Load (DirAddr 3) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Nop 
    , EndProg 
    ]

main = run [prog0]

